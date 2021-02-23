package com.huyqgtran.queenlive.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.huyqgtran.queenlive.domain.repository.DataRepository
import com.huyqgtran.queenlive.ui.ViewTour
import com.huyqgtran.queenlive.ui.mapper.ViewMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber

class TourViewModel(
    private val dataRepository: DataRepository,
    private val viewMapper: ViewMapper
) : ViewModel() {
    val tours: LiveData<List<ViewTour>> = dataRepository.getTours().map {
        viewMapper.tourToViewTour(it)
    } .catch {
        error -> Timber.e(error)
    }.asLiveData()
}