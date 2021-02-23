package com.huyqgtran.queenlive.data.localsrc.mapper

import com.huyqgtran.queenlive.data.localsrc.model.JsonTour
import com.huyqgtran.queenlive.domain.model.Show
import com.huyqgtran.queenlive.domain.model.Song
import com.huyqgtran.queenlive.domain.model.Tour
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter

class LocalMapperImpl : LocalMapper {

    override fun jsonTourToTour(jsonTour: JsonTour): Tour {
        return with(jsonTour) {
            Tour(
                name,
                convertIntToDate(date),
                url
            )
        }
    }

    private fun convertIntToDate(date: Int): Year = Year.of(date)

    override fun jsonShowToShow(jsonShow: String, tour: String): Show {
        val components = jsonShow.split("\\s".toRegex()) //"04.12.1979" "Newcastle,"  "UK"
        val date = LocalDate.parse(components[0].replace(".", "-")
            , DateTimeFormatter.ofPattern("dd-MM-yyyy")) // "04-12-1979"
        val name = components[1].replace(",", "") // "Newcastle"
        return Show(date, name, tour)
    }

    override fun jsonSongToSong(date: LocalDate, name: String, showName: String): Song = Song(date, name, showName)
}