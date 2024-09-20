package com.spyker3d.bininfo.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spyker3d.bininfo.history.domain.api.HistoryInteractor
import com.spyker3d.bininfo.search.domain.api.SearchBinUseCase
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import com.spyker3d.bininfo.utils.Resource
import com.spyker3d.bininfo.externalnavigator.domain.api.NavigatorInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchBinUseCase: SearchBinUseCase,
    val historyInteractor: HistoryInteractor,
    private val navigatorInteractor: NavigatorInteractor,
) : ViewModel() {
    private val _stateLiveData = MutableLiveData<SearchState>()
    val stateLiveData: LiveData<SearchState> = _stateLiveData

    private var searchJob: Job? = null

    fun processSearchRequest(binNumber: String) {
        searchJob = viewModelScope.launch {
            _stateLiveData.value = SearchState.Loading
            val cardInfo = searchBinUseCase.execute(binNumber)
            processResult(cardInfo)
        }
    }

    private suspend fun processResult(searchResult: Resource<CardInfo>) = when (searchResult) {
        is Resource.Success -> {
            _stateLiveData.value = SearchState.Content(searchResult.data!!)
            historyInteractor.insertCardInfo(searchResult.data)
        }

        is Resource.InternetConnectionError -> {
            _stateLiveData.value = SearchState.InternetConnectionError(searchResult.message!!)
        }

        is Resource.NotFoundError -> {
            _stateLiveData.value = SearchState.NotFoundError(searchResult.message!!)
        }

        is Resource.SocketTimeOutError -> {
            _stateLiveData.value = SearchState.SocketTimeOut(searchResult.message!!)
        }

        is Resource.Error -> {
            _stateLiveData.value = SearchState.ServerError(searchResult.message!!)
        }

        else -> {
            _stateLiveData.value = SearchState.ServerError(searchResult.message!!)
        }
    }

    fun openUrl(url: String) {
        navigatorInteractor.openUrl(url)
    }

    fun dialNumber(number: String) {
        navigatorInteractor.dialNumber(number)
    }

}