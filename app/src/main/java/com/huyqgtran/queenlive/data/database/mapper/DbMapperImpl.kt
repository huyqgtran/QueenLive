package com.huyqgtran.queenlive.data.database.mapper

import com.huyqgtran.queenlive.data.database.model.DbShow
import com.huyqgtran.queenlive.data.database.model.DbSong
import com.huyqgtran.queenlive.data.database.model.DbTour
import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour

class DbMapperImpl :DbMapper {
    override fun tourToDbTour(tour: Tour): DbTour {
        return with(tour) {
            DbTour(name, date, descriptionUrl)
        }
    }

    override fun showToDbShow(show: Show): DbShow {
        return with(show) {
            DbShow(date, name, tourName)
        }
    }

    override fun songToDbSong(song: Song): DbSong {
        return with(song) {
            DbSong(date, name)
        }
    }

    override fun listDbTourToTour(dbTour: List<DbTour>): List<Tour> {
        return dbTour.map {
            with(it) {
                Tour(name, date, descriptionUrl)
            }
        }
    }

    override fun listDbShowToShow(dbShow: List<DbShow>): List<Show> {
        return dbShow.map {
            with(it) {
                Show(date, name, tourName)
            }
        }
    }

    override fun listDbSongToSong(dbSong: List<DbSong>, showName: String): List<Song> {
        return dbSong.map {
            with(it) {
                Song(showDate, name, showName)
            }
        }
    }
}