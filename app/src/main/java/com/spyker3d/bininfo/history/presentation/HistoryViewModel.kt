package com.spyker3d.bininfo.history.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spyker3d.bininfo.history.domain.api.HistoryInteractor
import com.spyker3d.bininfo.search.domain.entities.CardInfo
import kotlinx.coroutines.launch

class HistoryViewModel(val historyInteractor: HistoryInteractor) : ViewModel() {
    private val _stateLiveData = MutableLiveData<HistoryState>()
    val stateLiveData: LiveData<HistoryState> = _stateLiveData

    init {
        viewModelScope.launch {
            historyInteractor.getAllCardInfoEntries().collect {
                _stateLiveData.value = if (it.isEmpty()) {
                    HistoryState.Empty
                } else {
                    HistoryState.Content(it)
                }
            }
        }
    }

    fun deleteCardInfoEntryFromHistory(cardInfo: CardInfo) {
        viewModelScope.launch {
            historyInteractor.deleteCardInfo(cardInfo.binNumber)
        }
    }
}