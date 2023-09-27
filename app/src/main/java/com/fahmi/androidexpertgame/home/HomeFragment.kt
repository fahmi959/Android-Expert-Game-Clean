package com.fahmi.androidexpertgame.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmi.androidexpertgame.R
import com.fahmi.androidexpertgame.core.data.Resource
import com.fahmi.androidexpertgame.core.ui.GameAdapter
import com.fahmi.androidexpertgame.databinding.FragmentHomeBinding
import com.fahmi.androidexpertgame.detail.DetailGameActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val gameAdapter = GameAdapter()
            gameAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGameActivity::class.java)
                intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.game.observe(viewLifecycleOwner, { game ->
                if (game != null) {
                    when (game) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            gameAdapter.setData(game.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.gamesErrorInclude.root.visibility = View.VISIBLE
                            binding.gamesErrorInclude.tvError.text = game.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvGames) {
                layoutManager = GridLayoutManager(requireContext(), 2) // 2 columns
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }

        // Add the FloatingActionButton click listener here
        binding.fab.setOnClickListener {
            try {
                startActivity(Intent(requireActivity(), Class.forName("com.fahmi.androidexpertgame.favorite.FavoriteActivity")))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Module not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}