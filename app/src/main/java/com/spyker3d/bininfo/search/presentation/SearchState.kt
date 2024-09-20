package com.spyker3d.bininfo.search.presentation

import com.spyker3d.bininfo.search.domain.entities.CardInfo

sealed interface SearchState {
    object Loading : SearchState

    class Content(val cardInfo: CardInfo) : SearchState

    class InternetConnectionError(val errorMessage: String) : SearchState

    class NotFoundError(val errorMessage: String) : SearchState

    class ServerError(val errorMessage: String) : SearchState

    class SocketTimeOut(val errorMessage: String) : SearchState

}