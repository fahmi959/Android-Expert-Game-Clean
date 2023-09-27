package com.fahmi.androidexpertgame.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

class ListGameResponse (

    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<GameResponse>


    )

