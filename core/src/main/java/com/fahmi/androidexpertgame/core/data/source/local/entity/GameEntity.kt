package com.fahmi.androidexpertgame.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


// Model Untuk tabel-tabel di dalam database
@Parcelize
@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "gameId")
    val gameId: Int,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "tba")
    val tba: Boolean,

    @ColumnInfo(name = "background_image")
    val backgroundImage: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "rating_top")
    val ratingTop: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

    // Include other fields as needed
): Parcelable