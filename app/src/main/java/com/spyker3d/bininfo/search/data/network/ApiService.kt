package com.spyker3d.bininfo.search.data.network

import com.spyker3d.bininfo.search.data.dto.BinSearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Accept-Version: 3")
    @GET("{query}")
    suspend fun searchBin(@Path("query") binNumber: String): BinSearchResponse // Какая аннотация для поиска по номеру карты? "Accept-Version: 3"

}
