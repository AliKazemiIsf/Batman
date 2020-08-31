package com.test.batman.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Tbl_MovieDetail",
    foreignKeys = [
        ForeignKey(
            entity = Movie::class,
            parentColumns = ["xImdbId"],
            childColumns = ["xImdbId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Rating::class,
            parentColumns = ["xRatingId"],
            childColumns = ["xImdbId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class MovieDetail {
    val xRated: String?= null
    val xReleased: String?= null
    val xRuntime: String?= null
    val xGenre: String?= null
    val xDirector: String?= null
    val xWriter: String?= null
    val xActors: String?= null
    val xPlot: String?= null
    val xLanguage: String?= null
    val xCountry: String?= null
    val xAwards: String?= null
    val xMetaScore: String?= null
    val xImdbRating: String?= null
    val xImdbVotes: String?= null
    val xDvd: String?= null
    val xBoxOffice: String?= null
    val xProduction: String?= null
    val xWebsite: String?= null
}