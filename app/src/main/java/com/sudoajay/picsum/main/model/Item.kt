package com.sudoajay.picsum.main.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemTable")
class Item(
    @PrimaryKey(autoGenerate = true ) var id: Long?,
    @ColumnInfo(name = "AuthorName") val name: String,
    @ColumnInfo(name = "width") val width: Int,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "download_url") val download_url: String,




    )