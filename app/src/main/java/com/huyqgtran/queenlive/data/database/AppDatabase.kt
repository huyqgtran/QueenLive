package com.huyqgtran.queenlive.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.huyqgtran.queenlive.data.database.dao.QueenLiveDao
import com.huyqgtran.queenlive.data.database.model.DbShow
import com.huyqgtran.queenlive.data.database.model.DbSong
import com.huyqgtran.queenlive.data.database.model.DbTour
import com.huyqgtran.queenlive.data.localsrc.worker.LocalSourceReader
import com.huyqgtran.queenlive.utilities.DB_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

@Database(entities = [DbTour::class, DbShow::class, DbSong::class], version = 1, exportSchema = false)
@TypeConverters(DbConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getQueenLiveDao(): QueenLiveDao
    companion object: KoinComponent {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        val localSrcReader: LocalSourceReader by inject()

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            localSrcReader.getDataFromJsonAndInsertToDB(context, getInstance(context).getQueenLiveDao())
                        }
                    }
                })
                .build()
        }
    }
}