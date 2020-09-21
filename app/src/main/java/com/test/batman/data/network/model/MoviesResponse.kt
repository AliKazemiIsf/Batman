package com.test.batman.data.network.model

import com.google.gson.annotations.SerializedName

class MoviesResponse {
    @SerializedName("Search")
    val search: List<Result>?= null

    inner class Result {
        @SerializedName("Title")
        val title: String?= null

        @SerializedName("Year")
        val year: String?= null

        @SerializedName("imdbID")
        val imdbId: String?= null

        @SerializedName("Type")
        val type: String?= null

        @SerializedName("Poster")
        val posterUrl: String?= null
    }
}