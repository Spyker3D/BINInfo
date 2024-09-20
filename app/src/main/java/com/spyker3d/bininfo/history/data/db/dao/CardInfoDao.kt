package com.spyker3d.bininfo.history.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spyker3d.bininfo.history.data.db.entity.CardInfoDb
import kotlinx.coroutines.flow.Flow

@Dao
interface CardInfoDao {
    @Insert(entity = CardInfoDb::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(cardInfo: CardInfoDb)

    @Delete(entity = CardInfoDb::class)
    suspend fun deleteEntity(cardInfo: CardInfoDb)

    @Query("SELECT * FROM cardinfo_table")
    fun getAllCardInfoEntries(): Flow<List<CardInfoDb>>

    @Query("SELECT * FROM cardinfo_table WHERE bin_number = :binNumber")
    suspend fun getCardInfoByBinNumber(binNumber: String): CardInfoDb

    @Query("DELETE FROM cardinfo_table WHERE bin_number = :binNumber")
    suspend fun deleteByBinNumber(binNumber: String)

}