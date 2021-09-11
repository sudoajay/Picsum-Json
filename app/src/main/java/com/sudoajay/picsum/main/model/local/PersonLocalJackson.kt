package com.sudoajay.picsum.main.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "PersonJacksonTable")
 class PersonLocalJackson(
    @PrimaryKey @field:JsonProperty("id") var id: Long?,
    @field:JsonProperty("author") val name: String,
    @field:JsonProperty("width") val width: Int,
    @field:JsonProperty("height") val height: Int,
    @field:JsonProperty("url") val openUrl: String,
    @field:JsonProperty("download_url") val downloadUrl: String
){
   constructor() : this(0,"",
      -0, 0, "",""
   )
 }