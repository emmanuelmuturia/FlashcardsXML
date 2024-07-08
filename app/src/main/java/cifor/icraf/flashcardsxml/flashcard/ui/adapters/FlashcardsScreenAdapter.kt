package cifor.icraf.flashcardsxml.flashcard.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import cifor.icraf.flashcardsxml.R
import cifor.icraf.flashcardsxml.flashcard.domain.entity.FlashcardEntity

class FlashcardsScreenAdapter : RecyclerView.Adapter<FlashcardsScreenAdapter.FlashcardsScreenViewHolder>() {

    var flashcards = listOf<FlashcardEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = flashcards.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardsScreenViewHolder =
        FlashcardsScreenViewHolder.inflateFrom(parent = parent)

    override fun onBindViewHolder(holder: FlashcardsScreenViewHolder, position: Int) {
        val item = flashcards[position]
        holder.bind(item = item)
    }

    class FlashcardsScreenViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {

        private val flashcardTerm = rootView.findViewById<TextView>(R.id.flashcardTerm)

        private val flashcardDefinition = rootView.findViewById<TextView>(R.id.flashcardDefinition)

        companion object {
            fun inflateFrom(parent: ViewGroup): FlashcardsScreenViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(
                    R.layout.flashcard_term,
                    parent,
                    false
                ) as CardView
                return FlashcardsScreenViewHolder(rootView = view)
            }
        }

        fun bind(item: FlashcardEntity) {
            flashcardTerm.text = item.flashCardTerm
            flashcardDefinition.text = item.flashCardDefinition
        }
    }

}