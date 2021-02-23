package com.huyqgtran.queenlive.di

import com.huyqgtran.queenlive.data.DataRepositoryImpl
import com.huyqgtran.queenlive.data.database.AppDatabase
import com.huyqgtran.queenlive.data.database.mapper.DbMapper
import com.huyqgtran.queenlive.data.database.mapper.DbMapperImpl
import com.huyqgtran.queenlive.data.localsrc.mapper.LocalMapper
import com.huyqgtran.queenlive.data.localsrc.mapper.LocalMapperImpl
import com.huyqgtran.queenlive.data.localsrc.worker.LocalSourceReader
import com.huyqgtran.queenlive.domain.repository.DataRepository
import com.huyqgtran.queenlive.ui.mapper.ViewMapper
import com.huyqgtran.queenlive.ui.mapper.ViewMapperImpl
import com.huyqgtran.queenlive.ui.viewmodels.ShowViewModel
import com.huyqgtran.queenlive.ui.viewmodels.SongViewModel
import com.huyqgtran.queenlive.ui.viewmodels.TourViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val MAIN_DISPATCHER = "main_dispatcher"
private const val BACKGROUND_DISPATCHER = "background_dispatcher"

val applicationModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single { AppDatabase.getInstance(androidContext()) }

    single { get<AppDatabase>().getQueenLiveDao() }

    single<ViewMapper> {
        ViewMapperImpl()
    }

    single<DbMapper> {
        DbMapperImpl()
    }

    single<LocalMapper> {
        LocalMapperImpl()
    }

    single {
        LocalSourceReader(get(), get(), get())
    }

    single(named(MAIN_DISPATCHER)) { Dispatchers.Main }

    single(named(BACKGROUND_DISPATCHER)) { Dispatchers.IO }

    single<DataRepository> {
        DataRepositoryImpl(get(), get(), get(), get(named(BACKGROUND_DISPATCHER)))
    }

    viewModel {
        TourViewModel(get(), get())
    }

    viewModel {
         (tourName: String) -> ShowViewModel(get(), get(), tourName)
    }
    
    viewModel { 
        (showDate: String) -> SongViewModel(get(), get(), showDate)
    }
}