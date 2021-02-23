package com.huyqgtran.queenlive.data

import com.huyqgtran.queenlive.data.database.dao.QueenLiveDao
import com.huyqgtran.queenlive.data.database.mapper.DbMapper
import com.huyqgtran.queenlive.data.localsrc.mapper.LocalMapper
import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour
import com.huyqgtran.queenlive.domain.repository.DataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class DataRepositoryImpl(
        private val dbMapper: DbMapper,
        private val localMapper: LocalMapper,
        private val queenLiveDao: QueenLiveDao,
        private val backgroundDispatcher: CoroutineDispatcher
) : DataRepository {
    override fun getTours(): Flow<List<Tour>> = queenLiveDao.getAllTours()
            .map {
                dbMapper.listDbTourToTour(it)
            }

    override fun getShows(tourName: String): Flow<List<Show>> = queenLiveDao.getShowsFromTour(tourName)
            .map {
                dbMapper.listDbShowToShow(it)
            }

    override fun getSongs(date: LocalDate): Flow<List<Song>> {
        val songsFlow = queenLiveDao.getSongsFromShow(date)
        val showFLow = queenLiveDao.getShowFromDate(date)
        return songsFlow.combine(showFLow) {
            songs, show -> dbMapper.listDbSongToSong(songs, show.name)
        }
    }
}