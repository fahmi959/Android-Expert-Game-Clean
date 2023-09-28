package com.fahmi.androidexpertgame.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.fahmi.androidexpertgame.core.ui.GameAdapter
import com.fahmi.androidexpertgame.detail.DetailGameActivity
import com.fahmi.androidexpertgame.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameAdapter = GameAdapter()
        gameAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteGame.observe(this, { dataGame ->
            gameAdapter.setData(dataGame)

        })

        with(binding.rvGame) {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 3) // 3 columns
            setHasFixedSize(true)
            adapter = gameAdapter
        }
    }
}
