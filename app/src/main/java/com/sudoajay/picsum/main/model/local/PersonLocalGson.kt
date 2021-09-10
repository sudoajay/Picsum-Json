package com.sudoajay.picsum.main.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PersonGsonTable")
class PersonLocalGson(
    @PrimaryKey @field:SerializedName("id")  var id: Long?,
    @field:SerializedName("author") val name: String,
    @field:SerializedName("width") val width: Int,
    @field:SerializedName("height") val height: Int,
    @field:SerializedName("url") val openUrl: String,
    @field:SerializedName("download_url") val downloadUrl: String
)