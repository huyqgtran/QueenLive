package com.huyqgtran.queenlive.domain.repo

import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour
import com.huyqgtran.queenlive.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate

class FakeRepository: DataRepository {
    override fun getTours(): Flow<List<Tour>> {
        TODO("Not yet implemented")
    }

    override fun getShows(tourName: String): Flow<List<Show>> {
        return flowOf(listOf(Show(LocalDate.now(), "Hammersmith", tourName)))
    }

    override fun getSongs(date: LocalDate): Flow<List<Song>> {
        TODO("Not yet implemented")
    }
}