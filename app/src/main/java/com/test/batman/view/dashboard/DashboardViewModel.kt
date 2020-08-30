package com.test.batman.view.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.batman.data.network.ApiInterface
import com.test.batman.data.network.model.BatmanMoviesResponse
import com.test.batman.data.network.model.MovieDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel(
    private val api: ApiInterface
) : ViewModel() {

    val error = MutableLiveData<String>()
    val listMovie = MutableLiveData<BatmanMoviesResponse>()
    val listMovieDetail = MutableLiveData<MovieDetailResponse>()

    fun getBatmanMovies() {
        val call = api.getBatmanMovies()
        call.enqueue(object : Callback<BatmanMoviesResponse> {
            override fun onResponse(
                call: Call<BatmanMoviesResponse>,
                response: Response<BatmanMoviesResponse>
            ) {
                val body = response.body()
                body?.let {
                    listMovie.postValue(it)
                } ?: kotlin.run {
                    error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<BatmanMoviesResponse>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }

    fun getMovieDetail(id: String) {
        val call = api.getMovieDetail(id)
        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                val body = response.body()
                body?.let {
                    listMovieDetail.postValue(it)
                } ?: kotlin.run {
                    error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }
}