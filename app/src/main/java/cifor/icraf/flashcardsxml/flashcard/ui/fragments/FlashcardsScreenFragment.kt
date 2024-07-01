package cifor.icraf.flashcardsxml.flashcard.ui.fragments

import FlashcardsPagerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity
import cifor.icraf.flashcardsxml.flashcard.ui.viewmodel.FlashcardsXMLViewModel
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch

class FlashcardsScreenFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var flashcardsPagerAdapter: FlashcardsPagerAdapter
    private val flashcardsXMLViewModel: FlashcardsXMLViewModel by viewModels<FlashcardsXMLViewModel>()
    private var flashcards = emptyList<FlashcardEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(
            R.layout.fragment_flashcards_screen,
            container,
            false
        )

        viewPager = rootView.findViewById(R.id.viewPager)
        val toolBar = rootView.findViewById<MaterialToolbar>(R.id.flashcardsScreenTopAppBar)

        toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        toolBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.btnAddFlashcard -> {
                    val parentActivity = requireActivity() as? FlashcardsListener
                    parentActivity?.onAddNewFlashcardClicked()
                    true
                }

                else -> false
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                flashcardsXMLViewModel.subjectUIState.collect { uiState ->
                    uiState.subjects.map { subjects ->
                        flashcards = subjects.flashcards
                    }
                }
            }
        }

        val addFlashcardsButton = rootView.findViewById<ImageButton>(R.id.btnAddFlashcard)
        addFlashcardsButton.setOnClickListener {
            val action = FlashcardsScreenFragmentDirections.actionFlashcardsScreenFragmentToFlashcardEditScreenFragment()
            findNavController().navigate(action)
        }

        viewPager = view?.findViewById(R.id.viewPager)!!
        flashcardsPagerAdapter = FlashcardsPagerAdapter(
            flashcards = flashcards
        )
        viewPager.adapter = flashcardsPagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Reset card to front side when page changes
                (viewPager.getChildAt(0) as? FlashcardsPagerAdapter.FlashcardViewHolder)?.flipCard()
            }
        })

        return rootView

    }

    interface FlashcardsListener {
        fun onAddNewFlashcardClicked()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FlashcardsScreenFragment()
    }

}