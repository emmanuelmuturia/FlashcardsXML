package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import cifor.icraf.flashcardsxml.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FlashcardsScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val flashcardsScreen = inflater.inflate(
            R.layout.fragment_flashcards_screen,
            container,
            false
        )

        val addFlashcardsButton = flashcardsScreen.findViewById<FloatingActionButton>(
            R.id.addFlashcardButton
        )

        val flashcardsScreenBackButton = flashcardsScreen.findViewById<ImageButton>(
            R.id.flashcardEditScreenBackButton
        )

        flashcardsScreenBackButton.setOnClickListener {
            flashcardsScreen.findNavController().navigateUp()
        }

        addFlashcardsButton.setOnClickListener {
            flashcardsScreen.findNavController().navigate(
                R.id.navigateToFlashcardsEditScreen
            )
        }

        return flashcardsScreen

    }

}