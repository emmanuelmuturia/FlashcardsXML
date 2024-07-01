package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import cifor.icraf.flashcardsxml.databinding.FragmentHomeScreenBinding
import cifor.icraf.flashcardsxml.flashcard.ui.adapters.SubjectAdapter
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import kotlinx.coroutines.launch

class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private val flashcardsXMLViewModel by viewModels<FlashcardsXMLViewModel>()
    private lateinit var subjectAdapter: SubjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        setupRecyclerView()

        observeFlashcardsXMLViewModel()

        return view

    }

    private fun observeFlashcardsXMLViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                flashcardsXMLViewModel.subjectUIState.collect { uiState ->
                    uiState.subjects.let { subjectsWithFlashcards ->
                        subjectAdapter.addNewList(subjectsWithFlashcards = subjectsWithFlashcards)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {

        subjectAdapter = SubjectAdapter(
            onSubjectClicked = {
                // Navigate to SubjectEditScreen...
            },
            onDeleteSubject = { subjectWithFlashcards ->
                flashcardsXMLViewModel.deleteSubject(subjectEntity = subjectWithFlashcards.subjectEntity)
            },
            subjects = emptyList()
        )

        binding.recyclerViewSubjects.apply {
            adapter = subjectAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}