package com.sudoajay.picsum.main.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize



@Parcelize
data class Person(
    @JsonProperty("id") val id: Int,
    @JsonProperty("author")val name: String,
    @JsonProperty("width")val width: Int,
    @JsonProperty("height")val height: Int,
    @JsonProperty("url")val openUrl: String,
    @JsonProperty("download_url")val downloadUrl: String,
): Parcelable