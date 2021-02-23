package com.huyqgtran.queenlive.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Year

@Entity(tableName = "tours")
data class DbTour (
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    val date: Year,
    val descriptionUrl: String
)