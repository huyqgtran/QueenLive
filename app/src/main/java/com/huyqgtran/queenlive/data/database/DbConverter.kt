package com.huyqgtran.queenlive.data.database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter

class DbConverter {
    @TypeConverter fun setLocalDateToString(date: LocalDate): String
    = date.toString()

    @TypeConverter fun setStringToLocalDate(string: String): LocalDate
    = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    @TypeConverter fun setYearToInt(year: Year): Int
    = year.value

    @TypeConverter fun setStringToYear(value: Int): Year
    = Year.of(value)
}