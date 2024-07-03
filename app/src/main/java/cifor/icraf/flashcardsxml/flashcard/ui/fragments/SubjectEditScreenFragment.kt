package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import cifor.icraf.flashcardsxml.R

class SubjectEditScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val subjectEditScreen = inflater.inflate(
            R.layout.fragment_subject_edit_screen,
            container,
            false
        )

        val subjectEditScreenBackButton = subjectEditScreen.findViewById<ImageButton>(
            R.id.flashcardEditScreenBackButton
        )

        val subjectEditScreenDoneButton = subjectEditScreen.findViewById<ImageButton>(
            R.id.flashcardEditScreenDoneButton
        )

        subjectEditScreenBackButton.setOnClickListener {
            subjectEditScreen.findNavController().navigateUp()
        }

        subjectEditScreenDoneButton.setOnClickListener {
            val navigationAction = SubjectEditScreenFragmentDirections.navigateBackToHomeScreen()
            subjectEditScreen.findNavController().navigate(
                navigationAction
            )
        }

        // Configure the EditText...

        return subjectEditScreen

    }

}