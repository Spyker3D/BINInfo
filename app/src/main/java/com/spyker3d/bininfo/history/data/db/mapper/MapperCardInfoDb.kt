package com.spyker3d.bininfo.history.data.db.mapper

import com.spyker3d.bininfo.history.data.db.entity.CardInfoDb
import com.spyker3d.bininfo.search.domain.entities.CardInfo

object MapperCardInfoDb {

    fun CardInfo.mapToDb(timeAdded: Long): CardInfoDb = CardInfoDb(
        binNumber = this.binNumber,
        cardDetails = this.cardDetails,
        bankDetails = this.bankDetails,
        timeAdded = timeAdded

    )

    fun CardInfoDb.mapToDomain(): CardInfo = CardInfo(
        binNumber = this.binNumber,
        cardDetails = this.cardDetails,
        bankDetails = this.bankDetails
    )

}