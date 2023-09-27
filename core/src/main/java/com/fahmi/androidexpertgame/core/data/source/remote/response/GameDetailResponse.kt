package com.fahmi.androidexpertgame.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GameDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("slug") val slug: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_original") val name_original: String,
    @SerializedName("description") val description: String,
    @SerializedName("metacritic") val metacritic: Int,
    // Add more properties as needed
): Parcelable