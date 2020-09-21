package com.test.batman.data.local

import com.test.batman.data.local.entity.Movie
import com.test.batman.data.local.entity.MovieDetail
import com.test.batman.data.local.entity.Rating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepository(
    private val database: BatmanDatabase
) {

    suspend fun insertMovie(movie: Movie){
        withContext(Dispatchers.IO){
            database.movieDao().insertMovie(movie)
        }
    }

    suspend fun insertMovieDetail(movieDetail: MovieDetail){
        withContext(Dispatchers.IO){
            database.movieDetailDao().insertMovieDetail(movieDetail)
        }
    }

    suspend fun insertRating(rating: Rating) {
        withContext(Dispatchers.IO){
            database.ratingDao().insertRating(rating)
        }
    }

    fun getMovies() = database.movieDao().getMovies()
    fun getMovieDetail(imdbId: String) = database.movieDetailDao().getMovieDetail(imdbId)
    fun getMovieRatings(imdbId: String) = database.ratingDao().getMovieRatings(imdbId)
}