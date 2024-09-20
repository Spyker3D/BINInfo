package com.spyker3d.bininfo.search.data.network

import com.spyker3d.bininfo.search.data.dto.BinSearchRequest
import com.spyker3d.bininfo.search.data.dto.NetworkResponse

interface NetworkClient {

    suspend fun doRequest(request: BinSearchRequest): NetworkResponse
}