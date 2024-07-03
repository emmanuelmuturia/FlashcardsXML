package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.databinding.FragmentHomeScreenBinding
import cifor.icraf.flashcardsxml.flashcard.ui.adapters.HomeScreenAdapter
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    private val flashcardsXMLViewModel by viewModels<FlashcardsXMLViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeScreenBinding.inflate(
            inflater,
            container,
            false
        )

        (activity as AppCompatActivity).setSupportActionBar(binding.homeScreenToolbar)

        binding.addSubjectButton.setOnClickListener {
            val navigationAction = HomeScreenFragmentDirections.navigateToSubjectEditScreen()
            this.findNavController().navigate(
                navigationAction
            )
        }

        // Add an onClickListener for the Card...

        val homeScreenAdapter = HomeScreenAdapter()
        binding.subjectList.adapter = homeScreenAdapter

        lifecycleScope.launch {
            flashcardsXMLViewModel.subjectUIState.collect { subjectUIState ->
                homeScreenAdapter.data = subjectUIState.subjects
            }
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}