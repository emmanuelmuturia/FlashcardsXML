package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.databinding.FragmentFlashcardsScreenBinding
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.ui.adapters.FlashcardsScreenAdapter
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class FlashcardsScreenFragment : Fragment() {

    private var _binding: FragmentFlashcardsScreenBinding? = null
    private val binding get() = _binding!!

    private val flashcardsXMLViewModel by viewModels<FlashcardsXMLViewModel>()

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

        val flashcardsScreenAdapter = FlashcardsScreenAdapter()
        binding.flashcardsList.adapter = flashcardsScreenAdapter

        val subjectName = FlashcardsScreenFragmentArgs.fromBundle(bundle = requireArguments()).subjectName

        lifecycleScope.launch {
            flashcardsScreenAdapter.flashcards = flashcardsXMLViewModel.getFlashcardsBySubjectName(subjectName = subjectName)
        }

        binding.flashcardsScreenBackButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        binding.addFlashcardButton.setOnClickListener {
            val navigationAction = FlashcardsScreenFragmentDirections.navigateToFlashcardsEditScreen(
                subjectEntity = SubjectEntity(
                    subjectName = ""
                ).toString()
            )
            this.findNavController().navigate(
                navigationAction
            )
        }

        return binding.root

    }

}