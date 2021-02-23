package com.huyqgtran.queenlive.domain.repository

import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface DataRepository {
    fun getTours(): Flow<List<Tour>>
    fun getShows(tourName: String): Flow<List<Show>>
    fun getSongs(date: LocalDate): Flow<List<Song>>
}