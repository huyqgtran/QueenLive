package com.huyqgtran.queenlive.domain.model

import java.time.Year

data class Tour(
    val name: String,
    val date: Year,
    val descriptionUrl: String
)