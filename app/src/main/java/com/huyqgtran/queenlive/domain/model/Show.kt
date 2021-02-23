package com.huyqgtran.queenlive.domain.model

import java.time.LocalDate

data class Show (
    val date: LocalDate,
    val name: String,
    val tourName: String
)