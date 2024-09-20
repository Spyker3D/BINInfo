package com.spyker3d.bininfo.history.presentation

import com.spyker3d.bininfo.search.domain.entities.CardInfo

interface HistoryState {
    object Empty : HistoryState

    class Content(val listOfHistoryRequests: List<CardInfo>) : HistoryState

}