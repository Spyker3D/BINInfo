package com.spyker3d.bininfo.history.presentation

import androidx.recyclerview.widget.RecyclerView
import com.spyker3d.bininfo.databinding.HistoryItemViewBinding
import com.spyker3d.bininfo.search.domain.entities.CardInfo

class CardInfoViewHolder(private val binding: HistoryItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(cardInfo: CardInfo) {
            binding.binNumber.text = cardInfo.binNumber
            binding.paymentNetworkInput.text = cardInfo.cardDetails.paymentNetwork
            binding.bankInput.text = cardInfo.bankDetails.name
        }
}