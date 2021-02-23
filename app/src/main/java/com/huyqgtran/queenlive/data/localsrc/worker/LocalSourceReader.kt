package com.huyqgtran.queenlive.data.localsrc.worker

import android.content.Context
import com.huyqgtran.queenlive.data.database.dao.QueenLiveDao
import com.huyqgtran.queenlive.data.database.mapper.DbMapper
import com.huyqgtran.queenlive.data.database.model.DbShow
import com.huyqgtran.queenlive.data.database.model.DbSong
import com.huyqgtran.queenlive.data.database.model.DbTour
import com.huyqgtran.queenlive.data.localsrc.mapper.LocalMapper
import com.huyqgtran.queenlive.data.localsrc.model.JsonTour
import com.huyqgtran.queenlive.utilities.JSON_FILE
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalSourceReader(private val localMapper: LocalMapper,
                        private val dbMapper: DbMapper,
                        private val moShi: Moshi) {
    suspend fun getDataFromJsonAndInsertToDB(context: Context, queenLiveDao: QueenLiveDao) {
        val localTours = readJson(context)
        val dbTourList = mutableListOf<DbTour>()
        val dbShowList = mutableListOf<DbShow>()
        val dbSongList = mutableListOf<DbSong>()
        for (jsonTour in localTours) {
            val domainTour = localMapper.jsonTourToTour(jsonTour)
            dbTourList.add(dbMapper.tourToDbTour(domainTour))

            val jsonSetList = jsonTour.setList
            val jsonShows = jsonTour.show
            for (show in jsonShows) {
                val domainShow = localMapper.jsonShowToShow(show, domainTour.name)
                dbShowList.add(dbMapper.showToDbShow(domainShow))

                for(song in jsonSetList) {
                    val domainSong = localMapper.jsonSongToSong(domainShow.date, song, domainShow.name)
                    dbSongList.add(dbMapper.songToDbSong(domainSong))
                }
            }
        }

        //insert data
        queenLiveDao.apply {
            insertAllTours(dbTourList)
            insertAllShows(dbShowList)
            insertAllSongs(dbSongList)
        }
    }

    private suspend fun readJson(context: Context): List<JsonTour> = withContext(Dispatchers.IO) {
        val listType = Types.newParameterizedType(List::class.java, JsonTour::class.java)
        val adapter: JsonAdapter<List<JsonTour>> = moShi.adapter(listType)

        val json = context.assets.open(JSON_FILE).bufferedReader().use{ it.readText()}
        adapter.fromJson(json)?: emptyList()
    }
}