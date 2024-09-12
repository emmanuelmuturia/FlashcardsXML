package cifor.icraf.flashcardsxml.flashcard.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards
import cifor.icraf.flashcardsxml.flashcard.ui.diffutil.SubjectDiffItemCallback

class HomeScreenAdapter(
    val onCardClicked: (String) -> Unit
) : ListAdapter<SubjectWithFlashcards, HomeScreenAdapter.SubjectItemViewHolder>(
    SubjectDiffItemCallback()
) {

    override fun onBindViewHolder(holder: SubjectItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item = item)
        holder.itemView.setOnClickListener {
            onCardClicked(
                item.subjectEntity.subjectName
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItemViewHolder =
        SubjectItemViewHolder.inflateFrom(parent)

    class SubjectItemViewHolder(rootView: CardView) : RecyclerView.ViewHolder(rootView) {

        private val subjectName = rootView.findViewById<TextView>(R.id.subjectName)

        companion object {
            fun inflateFrom(viewGroup: ViewGroup): SubjectItemViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val view = layoutInflater.inflate(
                    R.layout.subject_item,
                    viewGroup,
                    false
                ) as CardView
                return SubjectItemViewHolder(view)
            }
        }

        fun bind(item: SubjectWithFlashcards) {
            subjectName.text = item.subjectEntity.subjectName
        }
    }

}