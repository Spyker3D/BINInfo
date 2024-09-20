package com.spyker3d.bininfo.search.domain.entities

class CardDetails(
    val paymentNetwork: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val countryCode: String?,
    val countryAbbr: String?,
    val countryName: String?,
    val countryCurrency: String?,
    val countryLatitude: Int?,
    val countryLongitude: Int?,
    val numberLength: Int?,
    val luhn: Boolean?
)
