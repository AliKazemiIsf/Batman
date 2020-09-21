package com.test.batman.data.network

import com.test.batman.data.network.model.MoviesResponse
import com.test.batman.data.network.model.MovieDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("?apikey=3e974fca&s=batman")
    fun getBatmanMovies(): Call<MoviesResponse>

    @GET("?apikey=3e974fca")
    fun getMovieDetail(
        @Query("i")id: String
    ): Call<MovieDetailResponse>
}