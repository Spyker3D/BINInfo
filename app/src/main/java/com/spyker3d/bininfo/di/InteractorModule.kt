package com.spyker3d.bininfo.di

import com.spyker3d.bininfo.history.domain.api.HistoryInteractor
import com.spyker3d.bininfo.history.domain.interactor.HistoryInteractorImpl
import com.spyker3d.bininfo.search.domain.api.SearchBinUseCase
import com.spyker3d.bininfo.search.domain.usecase.SearchBinUseCaseImpl
import com.spyker3d.bininfo.externalnavigator.domain.api.NavigatorInteractor
import com.spyker3d.bininfo.externalnavigator.domain.interactor.NavigatorInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    factory<SearchBinUseCase> {
        SearchBinUseCaseImpl(binSearchRepository = get())
    }

    factory<HistoryInteractor> {
        HistoryInteractorImpl(historyRepository = get())
    }

    factory<NavigatorInteractor> {
        NavigatorInteractorImpl(navigatorRepository = get())
    }


}