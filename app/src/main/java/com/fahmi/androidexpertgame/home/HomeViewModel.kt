package com.fahmi.androidexpertgame.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fahmi.androidexpertgame.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val game = gameUseCase.getAllGame().asLiveData()

}

