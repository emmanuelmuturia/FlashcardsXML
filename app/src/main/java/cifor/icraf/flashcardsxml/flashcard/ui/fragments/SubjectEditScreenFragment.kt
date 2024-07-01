package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.flashcard.domain.entity.SubjectEntity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

class SubjectEditScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(
            R.layout.fragment_subject_edit_screen,
            container,
            false
        )

        val toolBar = rootView.findViewById<MaterialToolbar>(
            R.id.topAppBar
        )
        toolBar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
        toolBar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.btnDone -> {
                    val subjectName = rootView.findViewById<TextInputEditText>(R.id.editTextSubjectName).text.toString()
                    if (subjectName.isEmpty()) {

                    } else {
                        val parentActivity = requireActivity() as SubjectEditListener
                        parentActivity.onCompleteClick(subjectEntity = SubjectEntity(subjectName = subjectName))
                    }
                    true
                }
                else -> false
            }
        }

        return rootView

    }


    interface SubjectEditListener {
        fun onCompleteClick(subjectEntity: SubjectEntity)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SubjectEditScreenFragment()
    }
}