package com.test.batman.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.batman.data.local.entity.Rating

@Dao
interface RatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRating(rating: Rating)

    @Query("SELECT * FROM tbl_rating where xImdbId= :imdbId")
    fun getMovieRatings(imdbId: String): LiveData<List<Rating>>
}