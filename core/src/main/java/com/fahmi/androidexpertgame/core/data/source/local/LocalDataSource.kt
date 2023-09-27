package com.fahmi.androidexpertgame.core.data.source.local

import androidx.lifecycle.LiveData
import com.fahmi.androidexpertgame.core.data.source.local.entity.GameEntity
import com.fahmi.androidexpertgame.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource(private val gameDao: GameDao) {


    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGame()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }
}