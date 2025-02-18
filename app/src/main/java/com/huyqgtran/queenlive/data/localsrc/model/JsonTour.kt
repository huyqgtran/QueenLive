package com.huyqgtran.queenlive.data.localsrc.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class JsonTour(
    val name: String,
    val date: String,
    val url: String,
    @Json(name = "set_list") val setList: List<String>,
    val show: List<String>,
    @Json(name = "rarely_played_songs") val rarelyPlayedSongs:List<String>
)