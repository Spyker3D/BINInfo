package com.spyker3d.bininfo.history.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spyker3d.bininfo.databinding.HistoryItemViewBinding
import com.spyker3d.bininfo.search.domain.entities.CardInfo

class CardInfoAdapter(
    private var cardsList: List<CardInfo>,
    private val onCardClickListener: (CardInfo) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cardInfoViewBinding = HistoryItemViewBinding.inflate(inflater, parent, false)
        return CardInfoViewHolder(cardInfoViewBinding)
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CardInfoViewHolder) {
            holder.bind(cardsList[position])
            holder.itemView.setOnClickListener {
                onCardClickListener(cardsList[position])
            }
        }
    }

    fun updateList(updatedHistoryList: List<CardInfo>) {
        cardsList = updatedHistoryList
        notifyDataSetChanged()
    }

}