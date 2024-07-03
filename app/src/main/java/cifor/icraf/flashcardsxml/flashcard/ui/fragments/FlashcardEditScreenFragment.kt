package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import cifor.icraf.flashcardsxml.R

class FlashcardEditScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val flashcardEditScreen = inflater.inflate(
            R.layout.fragment_flashcard_edit_screen,
            container,
            false
        )

        val flashcardEditScreenBackButton = flashcardEditScreen.findViewById<ImageButton>(
            R.id.flashcardEditScreenBackButton
        )

        val flashcardEditScreenDoneButton = flashcardEditScreen.findViewById<ImageButton>(
            R.id.flashcardEditScreenDoneButton
        )

        flashcardEditScreenBackButton.setOnClickListener {
            flashcardEditScreen.findNavController().navigateUp()
        }

        flashcardEditScreenDoneButton.setOnClickListener {
            val navigationAction = FlashcardEditScreenFragmentDirections.navigateBackToFlashcardsScreen(
                subjectWithFlashcards =
            )
            flashcardEditScreen.findNavController().navigate(
                navigationAction
            )
        }

    }

}