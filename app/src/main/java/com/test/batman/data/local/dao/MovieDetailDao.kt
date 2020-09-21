package com.test.batman.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.batman.data.local.entity.MovieDetail

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetail: MovieDetail)

    @Query("SELECT * FROM tbl_movie_detail WHERE xImdbId= :imdbId")
    fun getMovieDetail(imdbId: String): LiveData<MovieDetail>
}