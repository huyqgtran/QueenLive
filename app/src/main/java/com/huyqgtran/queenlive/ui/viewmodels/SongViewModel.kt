package com.huyqgtran.queenlive.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.huyqgtran.queenlive.domain.repository.DataRepository
import com.huyqgtran.queenlive.ui.mapper.ViewMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SongViewModel(
        private val dataRepository: DataRepository,
        private val viewMapper: ViewMapper,
        private val date: String
) : ViewModel() {
    val songs = dataRepository.getSongs(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .map { viewMapper.songToViewSong(it) }
            .catch {
                error -> Timber.e(error)
            }
            .asLiveData()
}