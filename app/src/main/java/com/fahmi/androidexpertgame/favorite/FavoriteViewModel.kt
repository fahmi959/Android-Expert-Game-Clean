package com.fahmi.androidexpertgame.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fahmi.androidexpertgame.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val favoriteGame = gameUseCase.getFavoriteGame().asLiveData()

}

