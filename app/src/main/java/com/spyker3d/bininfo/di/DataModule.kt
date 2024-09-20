package com.spyker3d.bininfo.di

import com.google.android.material.navigation.NavigationBarItemView
import com.google.gson.Gson
import com.spyker3d.bininfo.history.data.db.AppDatabase
import com.spyker3d.bininfo.search.data.network.ApiService
import com.spyker3d.bininfo.search.data.network.NetworkClient
import com.spyker3d.bininfo.search.data.network.RetrofitNetworkClient
import com.spyker3d.bininfo.externalnavigator.data.navigator.Navigator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BINLIST_URL = "https://lookup.binlist.net/"

val dataModule = module {
    single<NetworkClient> {
        RetrofitNetworkClient(context = androidContext(), apiService = get())
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BINLIST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>()) // interception client
            .build()
            .create(ApiService::class.java)
    }

    single<OkHttpClient> {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }


    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory { Gson() }

    single<AppDatabase> {
        AppDatabase.getInstance(androidContext())
    }

    single {
        Navigator(context = androidContext())
    }

}