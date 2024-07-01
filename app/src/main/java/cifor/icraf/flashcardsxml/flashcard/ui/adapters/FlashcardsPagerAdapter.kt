import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity

class FlashcardsPagerAdapter(private val flashcards: List<FlashcardEntity>) :
    RecyclerView.Adapter<FlashcardsPagerAdapter.FlashcardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.flashcard_item, parent, false)
        return FlashcardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlashcardViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return flashcards.size
    }

    inner class FlashcardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layoutFront: View = itemView.findViewById(R.id.layoutFront)
        private val layoutBack: View = itemView.findViewById(R.id.layoutBack)
        private val textViewTerm: TextView = itemView.findViewById(R.id.textViewTerm)
        private val textViewDefinition: TextView = itemView.findViewById(R.id.textViewDefinition)

        private var flipped = false

        init {
            itemView.setOnClickListener {
                flipCard()
            }
        }

        fun bind(position: Int) {
            val flashcard = flashcards[position]
            textViewTerm.text = flashcard.flashCardTerm
            textViewDefinition.text = flashcard.flashCardDefinition
        }

        fun flipCard() {
            if (flipped) {
                flipToBack()
            } else {
                flipToFront()
            }
        }

        private fun flipToFront() {
            layoutFront.visibility = View.VISIBLE
            layoutBack.visibility = View.GONE
            layoutFront.animate().rotationY(0f).setDuration(600).start()
            flipped = false
        }

        private fun flipToBack() {
            layoutFront.visibility = View.GONE
            layoutBack.visibility = View.VISIBLE
            layoutBack.animate().rotationY(180f).setDuration(600).start()
            flipped = true
        }
    }
}