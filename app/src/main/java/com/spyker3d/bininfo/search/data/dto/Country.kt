package com.spyker3d.bininfo.search.data.dto

import com.google.gson.annotations.SerializedName

class Country(
    @SerializedName("numeric") val countryCode: String?,
    @SerializedName("alpha2") val countryAbbr: String?,
    @SerializedName("name") val countryName: String?,
    @SerializedName("emoji") val emoji: String?,
    @SerializedName("currency") val currency: String?,
    @SerializedName("latitude") val latitude: Int?,
    @SerializedName("longitude") val longitude: Int?
)
