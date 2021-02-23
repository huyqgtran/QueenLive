package com.huyqgtran.queenlive.ui

import android.net.Uri

data class ViewTour(
    val name: String,
    val date: String,
    val description: Uri
)

data class ViewShow(
    val date: String,
    val name: String,
    val tourName: String
)

data class ViewSong(
        val date: String,
        val name: String,
        val showName: String
)