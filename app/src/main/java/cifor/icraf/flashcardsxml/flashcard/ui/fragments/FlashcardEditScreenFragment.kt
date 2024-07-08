package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.databinding.FragmentFlashcardEditScreenBinding
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlashcardEditScreenFragment : Fragment() {

    private var _binding: FragmentFlashcardEditScreenBinding? = null
    private val binding = _binding!!

    private val flashcardsXMLViewModel by viewModel<FlashcardsXMLViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFlashcardEditScreenBinding.inflate(
            inflater,
            container,
            false
        )

        binding.flashcardsEditScreenBackButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        binding.flashcardsEditScreenDoneButton.setOnClickListener {
            flashcardsXMLViewModel.upsertFlashcard(flashcardEntity = FlashcardEntity(
                flashcardSubjectName = "",
                flashCardTerm = "",
                flashCardDefinition = ""
            ))
            this.findNavController().navigateUp()
        }

        return binding.root

    }

}