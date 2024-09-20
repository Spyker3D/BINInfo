package com.spyker3d.bininfo.search.data.network

import android.content.Context
import android.util.Log
import com.spyker3d.bininfo.search.data.dto.BinSearchRequest
import com.spyker3d.bininfo.search.data.dto.NetworkResponse
import com.spyker3d.bininfo.utils.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RetrofitNetworkClient(
    val context: Context,
    private val apiService: ApiService,
) : NetworkClient {
    override suspend fun doRequest(request: BinSearchRequest): NetworkResponse {
        if (!isConnected(context)) {
            return NetworkResponse().apply { resultCode = NetworkResponse.NO_INTERNET }
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchBin(request.binRequest)
                response.apply { resultCode = NetworkResponse.SUCCESS }
            } catch (e: HttpException) {
                if (e.code() == NetworkResponse.NOT_FOUND) {
                    NetworkResponse().apply {
                        errorMessage = e.message
                        resultCode = NetworkResponse.NOT_FOUND
                    }
                } else {
                    NetworkResponse().apply {
                        errorMessage = e.message
                        resultCode = NetworkResponse.FAILURE
                    }
                }
            } catch (e: SocketTimeoutException) {
                NetworkResponse().apply {
                    errorMessage = e.message
                    resultCode = NetworkResponse.SOCKET_TIMEOUT
                }
            } catch (e: UnknownHostException) {
                NetworkResponse().apply {
                    errorMessage = e.message
                    resultCode = NetworkResponse.FAILURE
                }
            } catch (e: Exception) {
                NetworkResponse().apply {
                    errorMessage = e.message
                }
            }
        }
    }

}