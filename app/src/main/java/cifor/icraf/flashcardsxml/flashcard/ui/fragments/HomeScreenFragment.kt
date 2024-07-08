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
import cifor.icraf.flashcardsxml.databinding.SubjectItemBinding
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards
import cifor.icraf.flashcardsxml.flashcard.ui.adapters.HomeScreenAdapter
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private var _subjectItemBinding: SubjectItemBinding? = null
    private val subjectItemBinding get() = _subjectItemBinding!!

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

        (activity as AppCompatActivity).setSupportActionBar(binding.homeScreenToolbar)

        binding.addSubjectButton.setOnClickListener {
            val navigationAction = HomeScreenFragmentDirections.navigateToSubjectEditScreen()
            this.findNavController().navigate(
                navigationAction
            )
        }

        _subjectItemBinding = SubjectItemBinding.inflate(
            inflater,
            container,
            false
        )

        val subjectName = subjectItemBinding.subjectName.text.toString()

        val homeScreenAdapter = HomeScreenAdapter(onCardClicked = {
            Log.d("Subject Card", "I have been clicked on...")
            val navigationAction = HomeScreenFragmentDirections.navigateToFlashcardsScreen()
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
        _subjectItemBinding = null
    }

}