package com.spyker3d.bininfo.history.data.repository

import com.spyker3d.bininfo.history.data.db.AppDatabase
import com.spyker3d.bininfo.history.domain.api.HistoryRepository
import com.spyker3d.bininfo.history.data.db.mapper.MapperCardInfoDb.mapToDb
import com.spyker3d.bininfo.history.data.db.mapper.MapperCardInfoDb.mapToDomain
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryRepositoryImpl(private val appDatabase: AppDatabase) : HistoryRepository {
    override suspend fun insertCardInfo(cardInfo: CardInfo) {
        val timeAdded = System.currentTimeMillis()
        appDatabase.cardInfoDao().insertEntity(cardInfo.mapToDb(timeAdded))
    }

    override suspend fun deleteCardInfo(binNumber: String) {
        appDatabase.cardInfoDao().deleteByBinNumber(binNumber)
    }

    override fun getAllCardInfoEntries(): Flow<List<CardInfo>> {
        val listOfHistoryCardsQuesry = appDatabase.cardInfoDao().getAllCardInfoEntries()
        return listOfHistoryCardsQuesry.map { list -> list.map { it.mapToDomain() } }
    }

    override suspend fun getCardInfoByBinNumber(binNumber: String): CardInfo =
        appDatabase.cardInfoDao().getCardInfoByBinNumber(binNumber).mapToDomain()

}