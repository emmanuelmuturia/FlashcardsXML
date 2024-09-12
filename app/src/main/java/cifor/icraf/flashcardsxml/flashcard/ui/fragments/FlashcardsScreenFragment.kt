package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import cifor.icraf.flashcardsxml.databinding.FragmentFlashcardsScreenBinding
import cifor.icraf.flashcardsxml.flashcard.ui.adapters.FlashcardsScreenAdapter
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsScreenViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlashcardsScreenFragment : Fragment() {

    private var _binding: FragmentFlashcardsScreenBinding? = null
    private val binding get() = _binding!!

    private val flashcardsScreenViewModel by viewModel<FlashcardsScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFlashcardsScreenBinding.inflate(
            inflater,
            container,
            false
        )

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.flashcardsScreenTopAppBar)
        }

        val flashcardsScreenAdapter = FlashcardsScreenAdapter()
        binding.flashcardsList.adapter = flashcardsScreenAdapter

        val subjectName =
            FlashcardsScreenFragmentArgs.fromBundle(bundle = requireArguments()).subjectName

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                flashcardsScreenViewModel.flashcards.collect { flashcards ->
                    flashcardsScreenAdapter.submitList(
                        flashcards.filter { it.flashcardSubjectName == subjectName }
                    )
                }
            }
        }

        binding.flashcardsScreenBackButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        binding.addFlashcardButton.setOnClickListener {
            val navigationAction =
                FlashcardsScreenFragmentDirections.navigateToFlashcardsEditScreen(
                    subjectName = subjectName
                )
            this.findNavController().navigate(
                navigationAction
            )
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        binding.flashcardsList.adapter = null
        viewLifecycleOwner.lifecycleScope.coroutineContext.cancelChildren()
    }

}