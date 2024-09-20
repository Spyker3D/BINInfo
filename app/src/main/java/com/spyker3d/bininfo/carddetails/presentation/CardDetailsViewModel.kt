package com.spyker3d.bininfo.carddetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spyker3d.bininfo.history.domain.api.HistoryInteractor
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import com.spyker3d.bininfo.externalnavigator.domain.api.NavigatorInteractor
import kotlinx.coroutines.launch

class CardDetailsViewModel(
    private val historyInteractor: HistoryInteractor,
    private val navigatorInteractor: NavigatorInteractor,
    private val binNumber: String
) : ViewModel() {
    private val _stateLiveData = MutableLiveData<CardInfo>()
    val stateLiveData: LiveData<CardInfo> = _stateLiveData

    init {
        viewModelScope.launch {
            val cardInfo = historyInteractor.getCardInfoByBinNumber(binNumber)
            _stateLiveData.value = cardInfo
        }
    }

    fun deleteCardInfoFromHistory(binNumber: String) {
        viewModelScope.launch {
            historyInteractor.deleteCardInfo(binNumber)
        }
    }

    fun openUrl(url: String) {
        navigatorInteractor.openUrl(url)
    }

    fun dialNumber(number: String) {
        navigatorInteractor.dialNumber(number)
    }
}