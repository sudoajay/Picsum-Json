package com.sudoajay.picsum.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val authorName: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String,
): Parcelable