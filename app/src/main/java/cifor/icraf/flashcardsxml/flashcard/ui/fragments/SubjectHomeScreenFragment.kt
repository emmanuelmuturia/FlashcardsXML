package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cifor.icraf.flashcardsxml.databinding.FragmentHomeScreenBinding
import cifor.icraf.flashcardsxml.flashcard.ui.adapters.HomeScreenAdapter
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubjectHomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private val flashcardsXMLViewModel by viewModel<FlashcardsXMLViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeScreenBinding.inflate(
            inflater,
            container,
            false
        )

       if (activity is AppCompatActivity) {
           (activity as AppCompatActivity).setSupportActionBar(binding.homeScreenToolbar)
       }

        binding.addSubjectButton.setOnClickListener {
            val navigationAction = SubjectHomeScreenFragmentDirections.navigateToSubjectEditScreen()
            this.findNavController().navigate(
                navigationAction
            )
        }

        val homeScreenAdapter = HomeScreenAdapter(onCardClicked = { subjectName ->
            Log.d("Subject Card", "I have been clicked on...")
            val navigationAction =
                SubjectHomeScreenFragmentDirections.navigateToFlashcardsScreen(
                    subjectName = subjectName
                )
            this.findNavController().navigate(
                navigationAction
            )
        })

        binding.subjectList.adapter = homeScreenAdapter

        lifecycleScope.launch {
            flashcardsXMLViewModel.subjectUIState.collect { subjectUIState ->
                homeScreenAdapter.data = subjectUIState.subjects
            }
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}