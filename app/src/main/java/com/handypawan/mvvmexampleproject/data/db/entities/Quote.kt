package com.handypawan.mvvmexampleproject.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by pawan on 08,June,2020
 */
@Entity
data class Quote(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val quote: String,
    val author: String,
    val thumbnail: String
//    val created_at: String = "Null",
//    val updated_at: String = "Null"
)
