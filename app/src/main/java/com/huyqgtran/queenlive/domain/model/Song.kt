package com.huyqgtran.queenlive.domain.model

import java.time.LocalDate

data class Song(
    val date: LocalDate,
    val name: String,
    val showName: String
)
