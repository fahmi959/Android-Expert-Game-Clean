package com.fahmi.androidexpertgame.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetail(
    val id: Int,
    val slug: String,
    val name: String,
    val name_original: String,
    val description: String,
    val metacritic: Int
    // Add more properties as needed
): Parcelable