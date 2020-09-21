package com.test.batman.view.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.batman.data.local.LocalRepository
import com.test.batman.data.local.entity.Movie
import com.test.batman.data.local.entity.MovieDetail
import com.test.batman.data.local.entity.Rating
import com.test.batman.data.network.ApiInterface
import com.test.batman.data.network.model.MovieDetailResponse
import com.test.batman.data.network.model.MoviesResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel(
    private val api: ApiInterface,
    private val repository: LocalRepository
) : ViewModel() {

    val error = MutableLiveData<String>()

    val listMovie = MutableLiveData<MoviesResponse>()
    val listMovieDetail = MutableLiveData<MovieDetailResponse>()

    fun moviesOffline() = repository.getMovies()

    fun movieDetailOffline(imdbId: String)=  repository.getMovieDetail(imdbId)

    fun movieRatings(imdbId: String) = repository.getMovieRatings(imdbId)


    fun getMoviesOnline() {
        val call = api.getBatmanMovies()
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val body = response.body()
                body?.let {
                    listMovie.postValue(it)
                    setMovies(it)

                } ?: kotlin.run {
                    error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }

    fun getMovieDetailOnline(id: String) {
        val call = api.getMovieDetail(id)
        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                val body = response.body()
                body?.let {
                    listMovieDetail.postValue(it)
                    setMovieDetail(it)

                } ?: kotlin.run {
                    error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }

    private fun setMovies(response: MoviesResponse) {
        response.search?.let {
            if (it.isNotEmpty())
                it.forEach { model ->
                    insertMovieToDb(
                        Movie().apply {
                            this.xImdbId = model.imdbId ?: ""
                            this.xTitle = model.title
                            this.xType = model.type
                            this.xYear = model.year
                            this.xPosterUrl = model.posterUrl
                        }
                    )
                }
        }
    }

    private fun setMovieDetail(model: MovieDetailResponse) {
        insertMovieDetailToDb(
            MovieDetail().apply {
                this.xImdbId = model.imdbId ?: ""
                this.xActors = model.actors
                this.xAwards = model.awards
                this.xBoxOffice = model.boxOffice
                this.xCountry = model.country
                this.xDirector = model.director
                this.xDvd = model.dvd
                this.xGenre = model.genre
                this.xImdbRating = model.imdbRating
                this.xImdbVotes = model.imdbVotes
                this.xLanguage = model.language
                this.xMetaScore = model.metaScore
                this.xPlot = model.plot
                this.xProduction = model.production
                this.xRated = model.rated
                this.xReleased = model.released
                this.xRuntime = model.runtime
                this.xWebsite = model.website
                this.xWriter = model.writer
                setRating(model.imdbId, model.rating)
            })
    }

    private fun setRating(
        imdbId: String?,
        rating: List<MovieDetailResponse.Rating>?
    ) {
        rating?.let {
            it.forEach { model ->
                insertRating(
                    Rating().apply {
                        this.xImdbId = imdbId ?: ""
                        this.xSource = model.source
                        this.xValue = model.value
                    }
                )
            }
        }
    }

    private fun insertRating(rating: Rating) {
        viewModelScope.launch {
            repository.insertRating(rating)
        }
    }

    private fun insertMovieToDb(movie: Movie) {
        viewModelScope.launch {
            repository.insertMovie(movie)
        }
    }

    private fun insertMovieDetailToDb(movieDetail: MovieDetail) {
        viewModelScope.launch {
            repository.insertMovieDetail(movieDetail)
        }
    }
}