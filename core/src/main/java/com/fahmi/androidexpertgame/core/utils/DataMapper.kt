package com.fahmi.androidexpertgame.core.utils

import com.fahmi.androidexpertgame.core.data.source.local.entity.GameEntity
import com.fahmi.androidexpertgame.core.data.source.remote.response.GameResponse
import com.fahmi.androidexpertgame.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                gameId = it.id,
                slug = it.slug,
                name = it.name,
                released = it.released,
                tba = it.tba,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingTop = it.rating_top,
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameId = it.gameId,
                slug = it.slug,
                name = it.name,
                released = it.released,
                tba = it.tba,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingTop = it.ratingTop,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.gameId,
        slug = input.slug,
        name = input.name,
        released = input.released,
        tba = input.tba,
        backgroundImage = input.backgroundImage,
        rating = input.rating,
        ratingTop = input.ratingTop,
        isFavorite = input.isFavorite
    )
}