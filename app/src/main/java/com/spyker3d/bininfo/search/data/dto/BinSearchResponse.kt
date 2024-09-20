package com.spyker3d.bininfo.search.data.dto

import com.google.gson.annotations.SerializedName

class BinSearchResponse(
    @SerializedName("number") val number: NumberResponse?,
    @SerializedName("scheme") val paymentNetwork: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("prepaid") val prepaid: Boolean?,
    @SerializedName("country") val country: Country?,
    @SerializedName("bank") val bank: Bank?
) : NetworkResponse()