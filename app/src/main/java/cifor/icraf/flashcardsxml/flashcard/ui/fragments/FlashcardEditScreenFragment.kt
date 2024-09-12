package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cifor.icraf.flashcardsxml.databinding.FragmentFlashcardEditScreenBinding
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlashcardEditScreenFragment : Fragment() {

    private var _binding: FragmentFlashcardEditScreenBinding? = null
    private val binding get() = _binding!!

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

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.flashcardsEditScreenTopAppBar)
        }

        binding.flashcardsEditScreenDoneButton.setOnClickListener {
            val subjectName =
                FlashcardEditScreenFragmentArgs.fromBundle(bundle = requireArguments()).subjectName

            val flashcardTerm = binding.flashcardTermEditText.text.toString()

            val flashcardDefinition = binding.flashcardDefinitionEditText.text.toString()

            val flashcardEntity = FlashcardEntity(
                flashcardSubjectName = subjectName,
                flashCardTerm = flashcardTerm,
                flashCardDefinition = flashcardDefinition
            )
            flashcardsXMLViewModel.upsertFlashcard(flashcardEntity = flashcardEntity)
            this.findNavController().navigateUp()
            Toast.makeText(
                context,
                "Flashcard has been saved successfully!",
                Toast.LENGTH_LONG
            ).show()
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}