package com.test.batman.data.network.model

import com.google.gson.annotations.SerializedName

class MovieDetailResponse {
    @SerializedName("Title")
    val title: String?= null

    @SerializedName("Year")
    val year: String?= null

    @SerializedName("Rated")
    val rated: String?= null

    @SerializedName("Released")
    val released: String?= null

    @SerializedName("Runtime")
    val runtime: String?= null

    @SerializedName("Genre")
    val genre: String?= null

    @SerializedName("Director")
    val director: String?= null

    @SerializedName("Writer")
    val writer: String?= null

    @SerializedName("Actors")
    val actors: String?= null

    @SerializedName("Plot")
    val plot: String?= null

    @SerializedName("Language")
    val language: String?= null

    @SerializedName("Country")
    val country: String?= null

    @SerializedName("Awards")
    val awards: String?= null

    @SerializedName("Poster")
    val posterUrl: String?= null

    @SerializedName("Metascore")
    val metaScore: String?= null

    @SerializedName("imdbRating")
    val imdbRating: String?= null

    @SerializedName("imdbVotes")
    val imdbVotes: String?= null

    @SerializedName("imdbID")
    val imdbId: String?= null

    @SerializedName("Type")
    val type: String?= null

    @SerializedName("DVD")
    val dvd: String?= null

    @SerializedName("BoxOffice")
    val boxOffice: String?= null

    @SerializedName("Production")
    val production: String?= null

    @SerializedName("Website")
    val website: String?= null

    @SerializedName("Ratings")
    val rating: List<Rating>?= null

    inner class Rating{
        @SerializedName("Source")
        val source: String?= null

        @SerializedName("Value")
        val value: String?= null
    }
}