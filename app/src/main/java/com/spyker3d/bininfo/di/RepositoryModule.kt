package com.spyker3d.bininfo.di

import com.spyker3d.bininfo.history.data.repository.HistoryRepositoryImpl
import com.spyker3d.bininfo.history.domain.api.HistoryRepository
import com.spyker3d.bininfo.search.data.repository.BinSearchRepositoryImpl
import com.spyker3d.bininfo.search.domain.api.BinSearchRepository
import com.spyker3d.bininfo.externalnavigator.data.repository.NavigatorRepositoryImpl
import com.spyker3d.bininfo.externalnavigator.domain.api.NavigatorRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<BinSearchRepository> {
        BinSearchRepositoryImpl(networkClient = get(), context = androidContext())
    }

    single<HistoryRepository> {
        HistoryRepositoryImpl(appDatabase = get())
    }

    single<NavigatorRepository> {
        NavigatorRepositoryImpl(externalNavigator = get())
    }

}