package com.huyqgtran.queenlive.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import java.time.LocalDate

@Entity(
    tableName = "songs",
    foreignKeys = [ForeignKey(
        entity = DbShow::class,
        parentColumns = ["date"],
        childColumns = ["show_date"]
    )],
    primaryKeys = ["show_date", "name"]
)
data class DbSong(
    @ColumnInfo(name = "show_date") val showDate: LocalDate,
    val name: String
)