package com.spyker3d.bininfo.search.data.repository

import android.content.Context
import com.spyker3d.bininfo.R
import com.spyker3d.bininfo.search.data.dto.BinSearchRequest
import com.spyker3d.bininfo.search.data.dto.BinSearchResponse
import com.spyker3d.bininfo.search.data.dto.NetworkResponse
import com.spyker3d.bininfo.search.data.mapper.MapperToCardInfo.mapToDomain
import com.spyker3d.bininfo.search.data.network.NetworkClient
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import com.spyker3d.bininfo.search.domain.api.BinSearchRepository
import com.spyker3d.bininfo.utils.Resource


class BinSearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val context: Context
) : BinSearchRepository {
    private val noInternetMessage = context.getString(R.string.no_internet_message)
    private val timeOutMessage = context.getString(R.string.time_out_message)
    private val serverErrorMessage = context.getString(R.string.server_error)
    private val notFoundMessage = context.getString(R.string.not_found_message)

    override suspend fun searchBin(cardNumber: String): Resource<CardInfo> {
        val response = networkClient.doRequest(BinSearchRequest(cardNumber))
        return when (response.resultCode) {

            NetworkResponse.SUCCESS -> {
                val cardInfo = (response as BinSearchResponse).mapToDomain(cardNumber)
                Resource.Success(data = cardInfo)
            }

            NetworkResponse.NO_INTERNET -> Resource.InternetConnectionError(noInternetMessage)

            NetworkResponse.SOCKET_TIMEOUT -> Resource.SocketTimeOutError(timeOutMessage)

            NetworkResponse.FAILURE -> Resource.Error(serverErrorMessage)

            NetworkResponse.NOT_FOUND -> Resource.NotFoundError(notFoundMessage)

            else -> Resource.Error(serverErrorMessage)
        }
    }

}