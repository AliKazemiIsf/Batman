package com.test.batman.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_rating")
class Rating {
    @PrimaryKey(autoGenerate = true)
    var xRatingId: Long = 0
    var xImdbId: String = ""
    var xSource: String?= null
    var xValue: String?= null
}