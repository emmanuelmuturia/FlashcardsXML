package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.databinding.FragmentFlashcardsScreenBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FlashcardsScreenFragment : Fragment() {

    private var _binding: FragmentFlashcardsScreenBinding? = null
    private val binding get() = _binding!!

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

        (activity as AppCompatActivity).setSupportActionBar(binding.flashcardsScreenTopAppBar)

        binding.flashcardsScreenBackButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        binding.addFlashcardButton.setOnClickListener {
            val navigationAction = FlashcardsScreenFragmentDirections.navigateToFlashcardsEditScreen(
                subjectEntity =
            )
            this.findNavController().navigate(
                navigationAction
            )
        }

        return binding.root

    }

}