package com.fahmi.androidexpertgame.core.data

import com.fahmi.androidexpertgame.core.data.source.remote.network.ApiResponse
import com.fahmi.androidexpertgame.core.data.source.local.LocalDataSource
import com.fahmi.androidexpertgame.core.data.source.local.entity.GameEntity
import com.fahmi.androidexpertgame.core.data.source.remote.RemoteDataSource
import com.fahmi.androidexpertgame.core.data.source.remote.response.GameResponse
import com.fahmi.androidexpertgame.core.domain.model.Game
import com.fahmi.androidexpertgame.core.domain.repository.IGameRepository
import com.fahmi.androidexpertgame.core.utils.AppExecutors
import com.fahmi.androidexpertgame.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: com.fahmi.androidexpertgame.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {



 override fun getAllGame(): Flow<com.fahmi.androidexpertgame.core.data.Resource<List<Game>>> =
        object : com.fahmi.androidexpertgame.core.data.NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGame()



            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()





    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map { DataMapper.mapEntitiesToDomain(it) }
    }


    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}

