package com.huyqgtran.queenlive.data.database.mapper

import com.huyqgtran.queenlive.data.database.model.DbShow
import com.huyqgtran.queenlive.data.database.model.DbSong
import com.huyqgtran.queenlive.data.database.model.DbTour
import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour

interface DbMapper {
    fun tourToDbTour(tour: Tour): DbTour
    fun showToDbShow(show: Show): DbShow
    fun songToDbSong(song: Song): DbSong
    fun listDbTourToTour(dbTour: List<DbTour>): List<Tour>
    fun listDbShowToShow(dbShow: List<DbShow>): List<Show>
    fun listDbSongToSong(dbSong: List<DbSong>, showName: String): List<Song>

}