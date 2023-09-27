package com.fahmi.androidexpertgame.core.domain.usecase


import com.fahmi.androidexpertgame.core.data.Resource
import com.fahmi.androidexpertgame.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(): Flow<com.fahmi.androidexpertgame.core.data.Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(game: Game, state: Boolean)


}