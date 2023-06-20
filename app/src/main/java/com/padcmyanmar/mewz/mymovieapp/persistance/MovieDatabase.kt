package com.padcmyanmar.mewz.mymovieapp.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.persistance.daos.MovieDao

@Database(entities = [MovieVO::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    companion object{
        const val DB_NAME = "THE_MOVIE_DB"

        var dbInstance: MovieDatabase? = null

        fun getDBInstance(context: Context): MovieDatabase? {
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun movieDao(): MovieDao
}