package com.sudoajay.picsum.main.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "PersonMoshiTable")
@JsonClass(generateAdapter = true)
class PersonLocalMoshi(
    @PrimaryKey @field:Json(name = "id")  var id: Long?,
    @field:Json(name="author") val name: String,
    @field:Json(name="width") val width: Int,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "url") val openUrl: String,
    @field:Json(name = "download_url") val downloadUrl: String
)