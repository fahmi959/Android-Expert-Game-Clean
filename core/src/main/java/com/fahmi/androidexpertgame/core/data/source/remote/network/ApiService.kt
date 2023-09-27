package com.fahmi.androidexpertgame.core.data.source.remote.network



import com.fahmi.androidexpertgame.core.data.source.remote.response.GameDetailResponse
import com.fahmi.androidexpertgame.core.data.source.remote.response.ListGameResponse
import retrofit2.Call


import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("games?key=37d77494f3aa4e1c90992005fe54e969")
   suspend fun getGames(): ListGameResponse

    @GET("games/{id}?key=37d77494f3aa4e1c90992005fe54e969")
    fun getGameDetails(@Path("id") gameId: Int): Call<GameDetailResponse>
}