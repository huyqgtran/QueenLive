package com.huyqgtran.queenlive.ui.mapper

import android.net.Uri
import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour
import com.huyqgtran.queenlive.ui.ViewShow
import com.huyqgtran.queenlive.ui.ViewSong
import com.huyqgtran.queenlive.ui.ViewTour
import java.time.format.DateTimeFormatter

class ViewMapperImpl : ViewMapper {

    override fun tourToViewTour(tour: List<Tour>): List<ViewTour> {
        return tour.map {
            with(it) {
                ViewTour(
                    name,
                    date,
                    Uri.parse(descriptionUrl)
                )
            }
        }
    }

    override fun showToViewShow(show: List<Show>): List<ViewShow> {
        return show.map {
            with(it) {
                ViewShow(
                    date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
                    name,
                    tourName
                )
            }
        }
    }

    override fun songToViewSong(song: List<Song>): List<ViewSong> {
        return song.map {
            with(it) {
                ViewSong(
                        date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(),
                        name,
                        showName,
                        rarePlayed
                )
            }
        }
    }
}