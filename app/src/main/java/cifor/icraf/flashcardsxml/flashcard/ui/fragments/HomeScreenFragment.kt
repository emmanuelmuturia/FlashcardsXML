package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import cifor.icraf.flashcardsxml.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val homeScreen = inflater.inflate(
            R.layout.fragment_home_screen,
            container,
            false
        )

        val addSubjectButton = homeScreen.findViewById<FloatingActionButton>(
            R.id.addSubjectButton
        )

        addSubjectButton.setOnClickListener {
            val navigationAction = HomeScreenFragmentDirections.navigateToSubjectEditScreen()
            homeScreen.findNavController().navigate(
                navigationAction
            )
        }

        return homeScreen

    }

}