package com.fahmi.androidexpertgame.core.data.source.remote


import android.util.Log
import com.fahmi.androidexpertgame.core.data.source.remote.network.ApiResponse
import com.fahmi.androidexpertgame.core.data.source.remote.network.ApiService
import com.fahmi.androidexpertgame.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getAllGame(): Flow<ApiResponse<List<GameResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getGames()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}