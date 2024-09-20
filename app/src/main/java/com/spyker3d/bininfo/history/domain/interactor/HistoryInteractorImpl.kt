package com.spyker3d.bininfo.history.domain.interactor

import com.spyker3d.bininfo.history.domain.api.HistoryInteractor
import com.spyker3d.bininfo.history.domain.api.HistoryRepository
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(private val historyRepository: HistoryRepository) : HistoryInteractor {
    override suspend fun insertCardInfo(cardInfo: CardInfo) {
        historyRepository.insertCardInfo(cardInfo)
    }

    override suspend fun deleteCardInfo(binNumber: String) {
        historyRepository.deleteCardInfo(binNumber)
    }

    override fun getAllCardInfoEntries(): Flow<List<CardInfo>> =
        historyRepository.getAllCardInfoEntries()

    override suspend fun getCardInfoByBinNumber(binNumber: String): CardInfo =
        historyRepository.getCardInfoByBinNumber(binNumber)
}