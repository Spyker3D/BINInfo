package com.spyker3d.bininfo.search.data.dto

import com.google.gson.annotations.SerializedName

class Bank(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("city") val city: String?
)
