package com.test.batman.data.local.entity

import androidx.room.PrimaryKey

class Rating {
    @PrimaryKey(autoGenerate = true)
    var xRatingId: Long?= null
    var xImdbId: String?= null
    var xSource: String?= null
    var xValue: String?= null
}