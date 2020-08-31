package com.test.batman.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.batman.data.local.dao.MovieDao
import com.test.batman.data.local.dao.MovieDetailDao
import com.test.batman.data.local.dao.RatingDao
import com.test.batman.data.local.entity.Movie
import com.test.batman.data.local.entity.MovieDetail
import com.test.batman.data.local.entity.Rating

@Database(
    entities = [
        (Movie::class),
        (MovieDetail::class),
        (Rating::class)
    ],
    version = 1,
    exportSchema = false
)
abstract class BatmanDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun ratingDao(): RatingDao
}