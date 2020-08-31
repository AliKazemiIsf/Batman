package com.test.batman.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tbl_Movie")
class Movie {
    @PrimaryKey(autoGenerate = false)
    var xImdbId: String?= null
    var xTitle: String?= null
    var xYear: String?= null
    var xType: String?= null
    var xPosterUrl: String?= null
}