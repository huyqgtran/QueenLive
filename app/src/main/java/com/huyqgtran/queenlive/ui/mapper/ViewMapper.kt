package com.huyqgtran.queenlive.ui.mapper

import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour
import com.huyqgtran.queenlive.ui.ViewShow
import com.huyqgtran.queenlive.ui.ViewSong
import com.huyqgtran.queenlive.ui.ViewTour

interface ViewMapper {

    fun tourToViewTour(tour: List<Tour>): List<ViewTour>

    fun showToViewShow(show: List<Show>): List<ViewShow>

    fun songToViewSong(song: List<Song>): List<ViewSong>
}