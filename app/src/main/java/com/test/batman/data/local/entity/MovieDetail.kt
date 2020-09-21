package com.test.batman.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_movie_detail")
class MovieDetail {
    @PrimaryKey
    var xImdbId: String =""
    var xRated: String?= null
    var xReleased: String?= null
    var xRuntime: String?= null
    var xGenre: String?= null
    var xDirector: String?= null
    var xWriter: String?= null
    var xActors: String?= null
    var xPlot: String?= null
    var xLanguage: String?= null
    var xCountry: String?= null
    var xAwards: String?= null
    var xMetaScore: String?= null
    var xImdbRating: String?= null
    var xImdbVotes: String?= null
    var xDvd: String?= null
    var xBoxOffice: String?= null
    var xProduction: String?= null
    var xWebsite: String?= null
}