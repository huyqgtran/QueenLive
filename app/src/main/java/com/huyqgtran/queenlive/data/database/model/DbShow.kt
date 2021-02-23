package com.huyqgtran.queenlive.data.database.model

import androidx.room.*
import java.time.LocalDate

@Entity(
    tableName = "shows",
    foreignKeys = [ForeignKey(
        entity = DbTour::class,
        parentColumns = ["name"],
        childColumns = ["tour_name"]
    )],
    indices = [Index("tour_name")]
)
data class DbShow(
    @PrimaryKey @ColumnInfo(name = "date") val date: LocalDate,
    val name: String,
    @ColumnInfo(name = "tour_name") val tourName: String
)