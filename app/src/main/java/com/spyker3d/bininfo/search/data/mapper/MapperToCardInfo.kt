package com.spyker3d.bininfo.search.data.mapper

import com.spyker3d.bininfo.search.data.dto.BinSearchResponse
import com.spyker3d.bininfo.search.domain.entities.BankDetails
import com.spyker3d.bininfo.search.domain.entities.CardDetails
import com.spyker3d.bininfo.search.domain.entities.CardInfo

object MapperToCardInfo {

    fun BinSearchResponse.mapToDomain(binNumber: String): CardInfo = CardInfo(
        binNumber = binNumber,
        cardDetails = this.mapToCardDetails(),
        bankDetails = this.mapToBankDetails()
    )

    private fun BinSearchResponse.mapToCardDetails(): CardDetails = CardDetails(
        paymentNetwork = this.paymentNetwork,
        type = this.type,
        brand = this.brand,
        prepaid = this.prepaid,
        countryCode = this.country?.countryCode,
        countryAbbr = this.country?.countryAbbr,
        countryName = this.country?.countryName,
        countryCurrency = this.country?.currency,
        countryLatitude = this.country?.latitude,
        countryLongitude = this.country?.longitude,
        numberLength = this.number?.length,
        luhn = this.number?.luhn
    )

    private fun BinSearchResponse.mapToBankDetails(): BankDetails = BankDetails(
        name = this.bank?.name,
        url = this.bank?.url,
        phone = this.bank?.phone,
        city = this.bank?.city
    )

}
