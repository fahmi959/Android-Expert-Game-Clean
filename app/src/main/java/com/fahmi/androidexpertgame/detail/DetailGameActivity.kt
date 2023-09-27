package com.fahmi.androidexpertgame.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fahmi.androidexpertgame.R
import com.fahmi.androidexpertgame.core.domain.model.Game
import com.fahmi.androidexpertgame.databinding.ActivityDetailGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGameActivity : AppCompatActivity() {

    private val detailGameViewModel: DetailGameViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val detailGame = intent.getParcelableExtra<Game>(EXTRA_DATA)
        showDetailGame(detailGame)

        // Fetch and observe game details
        detailGameViewModel.fetchGameDetail(detailGame?.gameId ?: 0)
        detailGameViewModel.gameDetail.observe(this, Observer { gameDetail ->
            // Update UI with game details
            val gameId = findViewById<TextView>(R.id.gameId)
            gameId.text = gameDetail.id.toString()
            val gameSlug = findViewById<TextView>(R.id.gameSlug)
            gameSlug.text = gameDetail.slug
            val gameNameOriginal = findViewById<TextView>(R.id.gameNameOriginal)
            gameNameOriginal.text = gameDetail.name_original
            val gameDescription = findViewById<TextView>(R.id.gameDescription)
            gameDescription.text = gameDetail.description
            val gameMetacritic = findViewById<TextView>(R.id.gameMetacritic)
            gameMetacritic.text = gameDetail.metacritic.toString()
            // Update other UI elements as needed
        })

    }

    private fun showDetailGame(detailGame: Game?) {
        detailGame?.let {
            supportActionBar?.title = detailGame.name

//            binding.content.tvDetailDescription.text = detailGame.slug //description


            Glide.with(this@DetailGameActivity)
                .load(detailGame.backgroundImage)
                .into(binding.ivDetailImage)

            var statusFavorite = detailGame.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailGameViewModel.setFavoriteGame(detailGame, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}
