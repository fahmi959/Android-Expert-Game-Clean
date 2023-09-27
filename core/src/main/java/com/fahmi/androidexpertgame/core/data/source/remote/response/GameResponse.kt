package com.fahmi.androidexpertgame.core.data.source.remote.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String,
    @SerializedName("released") val released: String,
    @SerializedName("tba") val tba: Boolean,
    @SerializedName("background_image") val backgroundImage: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("rating_top") val rating_top: Int,
    // properti lainnya
    @SerializedName("isFavorite") val isFavorite: Boolean

): Parcelable