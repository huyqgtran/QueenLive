package com.huyqgtran.queenlive.data.localsrc.mapper

import com.huyqgtran.queenlive.data.localsrc.model.JsonTour
import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour
import java.time.LocalDate

interface LocalMapper {

    fun jsonTourToTour(jsonTour: JsonTour): Tour

    fun jsonShowToShow(jsonShow: String, tour: String): Show

    fun jsonSongToSong(date: LocalDate, name: String, showName: String, rarelyPlayed: Boolean): Song
}