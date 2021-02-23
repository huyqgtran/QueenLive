package com.huyqgtran.queenlive.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.huyqgtran.queenlive.domain.repository.DataRepository
import com.huyqgtran.queenlive.ui.ViewShow
import com.huyqgtran.queenlive.ui.mapper.ViewMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber

class ShowViewModel(
    private val dataRepository:  DataRepository,
    private val viewMapper: ViewMapper,
    private val tourName: String
) : ViewModel() {
    val shows: LiveData<List<ViewShow>> = dataRepository.getShows(tourName).map {
        viewMapper.showToViewShow(it)
    }.catch { error ->
        Timber.e(error)
    }.asLiveData()
}