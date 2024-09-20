package com.spyker3d.bininfo.history.domain.api

import com.spyker3d.bininfo.search.domain.entities.CardInfo
import kotlinx.coroutines.flow.Flow

interface HistoryInteractor {
    suspend fun insertCardInfo(cardInfo: CardInfo)

    suspend fun deleteCardInfo(binNumber: String)

    fun getAllCardInfoEntries(): Flow<List<CardInfo>>

    suspend fun getCardInfoByBinNumber(binNumber: String): CardInfo
}