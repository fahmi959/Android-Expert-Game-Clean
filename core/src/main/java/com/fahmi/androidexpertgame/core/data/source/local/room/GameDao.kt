package com.fahmi.androidexpertgame.core.data.source.local.room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fahmi.androidexpertgame.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {


    @Query("SELECT * FROM games")
    fun getAllGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games where isFavorite = 1")
    fun getFavoriteGame(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Update
    fun updateFavoriteGame(game: GameEntity)


    // Misalnya, Anda dapat menambahkan fungsi lain untuk melakukan query atau operasi lain pada database
}
