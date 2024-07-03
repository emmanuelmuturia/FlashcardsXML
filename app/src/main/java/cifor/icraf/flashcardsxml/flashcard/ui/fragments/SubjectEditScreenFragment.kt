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
import cifor.icraf.flashcardsxml.databinding.FragmentSubjectEditScreenBinding
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel

class SubjectEditScreenFragment : Fragment() {

    private var _binding: FragmentSubjectEditScreenBinding? = null
    private val binding get() = _binding!!

    private val flashcardsXMLViewModel by viewModels<FlashcardsXMLViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSubjectEditScreenBinding.inflate(
            inflater,
            container,
            false
        )

        binding.subjectEditScreenBackButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        binding.subjectEditScreenDoneButton.setOnClickListener {
            val subjectName = binding.editTextSubjectName.text.toString()
            flashcardsXMLViewModel.upsertSubject(subjectEntity = SubjectEntity(
                subjectName = subjectName
            ))
            this.findNavController().navigateUp()
        }

        // Configure the EditText...

        return binding.root

    }

}