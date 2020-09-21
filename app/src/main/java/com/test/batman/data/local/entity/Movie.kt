package com.test.batman.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_movie")
class Movie {
    @PrimaryKey(autoGenerate = false)
    var xImdbId: String = ""
    var xTitle: String?= null
    var xYear: String?= null
    var xType: String?= null
    var xPosterUrl: String?= null
}