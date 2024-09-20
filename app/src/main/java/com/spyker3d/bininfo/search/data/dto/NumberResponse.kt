package com.spyker3d.bininfo.search.data.dto

import com.google.gson.annotations.SerializedName

class NumberResponse(
    @SerializedName("length") val length: Int?,
    @SerializedName("luhn") val luhn: Boolean?
)
