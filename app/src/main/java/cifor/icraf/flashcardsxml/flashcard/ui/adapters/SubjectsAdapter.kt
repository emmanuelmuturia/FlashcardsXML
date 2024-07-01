package cifor.icraf.flashcardsxml.flashcard.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.flashcard.domain.relations.SubjectWithFlashcards

class SubjectAdapter(
    private var subjects: List<SubjectWithFlashcards>,
    private val onSubjectClicked: (SubjectWithFlashcards) -> Unit,
    private val onDeleteSubject: (SubjectWithFlashcards) -> Unit
) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    inner class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.cardView)
        private val textViewSubjectName: TextView = itemView.findViewById(R.id.textViewSubjectName)
        private val textViewFlashcardsCount: TextView = itemView.findViewById(R.id.textViewFlashcardsCount)
        private val imageViewOptions: ImageView = itemView.findViewById(R.id.imageViewOptions)
        private val dropdownMenu: View = itemView.findViewById(R.id.dropdownMenu)

        init {
            cardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onSubjectClicked(subjects[position])
                }
            }

            cardView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteSubject(subjects[position])
                }
                true
            }

            imageViewOptions.setOnClickListener {
                dropdownMenu.visibility = if (dropdownMenu.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }

            itemView.findViewById<View>(R.id.textViewDelete)?.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteSubject(subjects[position])
                }
                dropdownMenu.visibility = View.GONE
            }
        }

        fun bind(subject: SubjectWithFlashcards) {
            textViewSubjectName.text = subject.subjectEntity.subjectName
            textViewFlashcardsCount.text = "${subject.flashcards.size} flashcards"
        }
    }

    fun addNewList(subjectsWithFlashcards: List<SubjectWithFlashcards>) {
        subjects = subjectsWithFlashcards
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_subject, parent, false)
        return SubjectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(subjects[position])
    }

    override fun getItemCount(): Int {
        return subjects.size
    }
}