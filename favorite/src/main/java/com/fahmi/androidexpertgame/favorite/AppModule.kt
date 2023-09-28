package com.fahmi.androidexpertgame.favorite

import com.fahmi.androidexpertgame.core.domain.usecase.GameInteractor
import com.fahmi.androidexpertgame.core.domain.usecase.GameUseCase
import com.fahmi.androidexpertgame.detail.DetailGameViewModel
import com.fahmi.androidexpertgame.favorite.FavoriteViewModel
import com.fahmi.androidexpertgame.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { com.fahmi.androidexpertgame.favorite.FavoriteViewModel(get()) }
    viewModel { DetailGameViewModel(get(), get()) }
}