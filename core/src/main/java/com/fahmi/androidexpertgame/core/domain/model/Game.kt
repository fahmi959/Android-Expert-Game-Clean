package com.fahmi.androidexpertgame.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val gameId: Int,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    val backgroundImage: String,
    val rating: Double,
    val ratingTop: Int,
    val isFavorite: Boolean
): Parcelable
