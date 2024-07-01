package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

class FlashcardEditScreenFragment : Fragment() {

    private lateinit var flashcardsXMLViewModel: FlashcardsXMLViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_flashcard_edit_screen, container, false)

        // Initialize ViewModel
        flashcardsXMLViewModel = ViewModelProvider(this)[FlashcardsXMLViewModel::class.java]

        // Inflate the edit screen layout
        //val editScreenView = inflater.inflate(R.layout.flashcard_edit_screen, container, false)
        //container?.addView(editScreenView)

        // Setup toolbar
        val toolbar = rootView.findViewById<MaterialToolbar>(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        // Setup buttons
        val btnBack = toolbar.findViewById<ImageButton>(R.id.btnBack)
        val btnDone = toolbar.findViewById<ImageButton>(R.id.btnDone)

        // Get references to text fields
        val editTextFlashcardTerm = rootView.findViewById<TextInputEditText>(R.id.editTextFlashcardTerm)
        val editTextFlashcardDefinition = rootView.findViewById<TextInputEditText>(R.id.editTextFlashcardDefinition)

        // Handle back button click
        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Handle done button click
        btnDone.setOnClickListener {
            val term = editTextFlashcardTerm.text.toString().trim()
            val definition = editTextFlashcardDefinition.text.toString().trim()

            if (term.isEmpty() || definition.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a term and definition", Toast.LENGTH_SHORT).show()
            } else {
                val flashcardEntity = FlashcardEntity(
                    flashCardTerm = term,
                    flashCardDefinition = definition,
                    flashcardSubjectName = "Your Subject Name" // Replace with actual subject name logic
                )
                flashcardsXMLViewModel.upsertFlashcard(flashcardEntity)
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        return rootView

    }

}