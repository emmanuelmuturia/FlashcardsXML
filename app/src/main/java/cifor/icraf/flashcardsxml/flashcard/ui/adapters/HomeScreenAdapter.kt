package cifor.icraf.flashcardsxml.flashcard.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards

class HomeScreenAdapter(
    val onCardClicked: () -> Unit
) : RecyclerView.Adapter<HomeScreenAdapter.SubjectItemViewHolder>() {

    var data = listOf<SubjectWithFlashcards>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: SubjectItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item = item)
        holder.itemView.setOnClickListener {
            onCardClicked()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItemViewHolder = SubjectItemViewHolder.inflateFrom(parent)

    override fun getItemCount(): Int = data.size

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