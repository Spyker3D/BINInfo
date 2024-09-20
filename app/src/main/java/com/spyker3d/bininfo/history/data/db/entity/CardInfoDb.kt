package com.spyker3d.bininfo.history.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.spyker3d.bininfo.search.domain.entities.BankDetails
import com.spyker3d.bininfo.search.domain.entities.CardDetails

@Entity(tableName = "cardinfo_table", indices = [Index(value = ["bin_number"], unique = true)])
data class CardInfoDb(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "card_note_id")
    val id: Int = 0,
    @ColumnInfo(name = "bin_number")
    val binNumber: String,
    @Embedded val cardDetails: CardDetails,
    @Embedded val bankDetails: BankDetails,
    @ColumnInfo(name = "time_added")
    val timeAdded: Long
)