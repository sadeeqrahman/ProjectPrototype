package com.sadeeq.app.projectprototype.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String? = null,

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}
