package com.spyker3d.bininfo.history.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.spyker3d.bininfo.history.data.db.dao.CardInfoDao
import com.spyker3d.bininfo.history.data.db.entity.CardInfoDb

@Database(
    version = 1,
    entities = [
        CardInfoDb::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardInfoDao(): CardInfoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                return instance ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database.db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also {
                        instance = it
                    }
            }
        }
    }
}