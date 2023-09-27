package com.fahmi.androidexpertgame.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmi.androidexpertgame.core.data.source.remote.network.ApiService
import com.fahmi.androidexpertgame.core.data.source.remote.response.GameDetailResponse
import com.fahmi.androidexpertgame.core.domain.model.Game
import com.fahmi.androidexpertgame.core.domain.model.GameDetail
import com.fahmi.androidexpertgame.core.domain.usecase.GameUseCase
import com.fahmi.androidexpertgame.core.utils.DataMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailGameViewModel(
    private val gameUseCase: GameUseCase,
    private val apiService: ApiService
) : ViewModel() {
    private val _gameDetail = MutableLiveData<GameDetail>()
    val gameDetail: LiveData<GameDetail>
        get() = _gameDetail

    fun setFavoriteGame(game: Game, newStatus: Boolean) = gameUseCase.setFavoriteGame(game, newStatus)

    fun fetchGameDetail(gameId: Int) {
        apiService.getGameDetails(gameId).enqueue(object : Callback<GameDetailResponse> {
            override fun onResponse(call: Call<GameDetailResponse>, response: Response<GameDetailResponse>) {
                if (response.isSuccessful) {
                    // Convert the response to your GameDetail model
                    val gameDetailResponse = response.body()
                    val gameDetail = DataMapper.mapGameDetailResponseToDomain(gameDetailResponse)
                    _gameDetail.value = gameDetail
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    object DataMapper {
        // Other mapping functions

        fun mapGameDetailResponseToDomain(response: GameDetailResponse?): GameDetail {
            return GameDetail(
                id = response?.id ?: 0,
                slug = response?.slug ?: "",
                name = response?.name ?: "",
                name_original = response?.name_original ?: "",
                description = response?.description ?: "",
                metacritic = response?.metacritic ?: 0
                // Map other properties as needed
            )
        }
    }
}