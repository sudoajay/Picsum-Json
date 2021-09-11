package com.sudoajay.picsum.main.model.remote

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PersonMoshi(
    @Json(name = "id") val id: Int,
    @Json(name = "author") val name: String,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "url") val openUrl: String,
    @Json(name = "download_url") val downloadUrl: String,
) : Parcelable

