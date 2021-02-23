package com.huyqgtran.queenlive.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.huyqgtran.queenlive.data.database.model.DbShow
import com.huyqgtran.queenlive.data.database.model.DbSong
import com.huyqgtran.queenlive.data.database.model.DbTour
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface QueenLiveDao {
    @Insert
    suspend fun insertAllShows(shows: List<DbShow>)

    @Insert
    suspend fun insertAllTours(tours: List<DbTour>)

    @Insert
    suspend fun insertAllSongs(songs: List<DbSong>)

    @Query("SELECT * FROM tours")
    fun getAllTours(): Flow<List<DbTour>>

    @Query("SELECT * FROM shows WHERE tour_name = :tourName ORDER BY date")
    fun getShowsFromTour(tourName: String): Flow<List<DbShow>>

    @Query("SELECT * FROM songs WHERE show_date = :date ORDER BY rowId")
    fun getSongsFromShow(date: LocalDate): Flow<List<DbSong>>

    @Query("SELECT * FROM shows WHERE date = :showDate")
    fun getShowFromDate(showDate: LocalDate): Flow<DbShow>
}