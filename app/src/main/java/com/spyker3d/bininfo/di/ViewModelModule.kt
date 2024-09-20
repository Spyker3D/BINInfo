package com.spyker3d.bininfo.di

import com.spyker3d.bininfo.carddetails.presentation.CardDetailsViewModel
import com.spyker3d.bininfo.history.presentation.HistoryViewModel
import com.spyker3d.bininfo.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<HistoryViewModel> {
        HistoryViewModel(historyInteractor = get())
    }

    viewModel<SearchViewModel> {
        SearchViewModel(
            searchBinUseCase = get(),
            historyInteractor = get(),
            navigatorInteractor = get()
        )
    }

    viewModel<CardDetailsViewModel> { (binNumber: String) ->
        CardDetailsViewModel(
            historyInteractor = get(),
            navigatorInteractor = get(),
            binNumber = get { parametersOf(binNumber) }
        )
    }

}
