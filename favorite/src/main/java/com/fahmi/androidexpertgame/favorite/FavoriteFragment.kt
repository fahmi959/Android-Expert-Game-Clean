//package com.fahmi.androidexpertgame.favorite
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.GridLayoutManager
//import com.fahmi.androidexpertgame.core.ui.GameAdapter
//import com.fahmi.androidexpertgame.databinding.FragmentFavoriteBinding
//import com.fahmi.androidexpertgame.detail.DetailGameActivity
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class FavoriteFragment : Fragment() {
//
//    private val favoriteViewModel: FavoriteViewModel by viewModel()
//
//
//
//    private var _binding: FragmentFavoriteBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        if (activity != null) {
//
//            val gameAdapter = GameAdapter()
//            gameAdapter.onItemClick = { selectedData ->
//                val intent = Intent(activity, DetailGameActivity::class.java)
//                intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
//                startActivity(intent)
//            }
//
//
//
//            favoriteViewModel.favoriteGame.observe(viewLifecycleOwner, { dataGame ->
//                gameAdapter.setData(dataGame)
//                binding.viewEmpty.root.visibility = if (dataGame.isNotEmpty()) View.GONE else View.VISIBLE
//            })
//
//            with(binding.rvGame) {
//                layoutManager = GridLayoutManager(requireContext(), 3) // 3 columns
//                setHasFixedSize(true)
//                adapter = gameAdapter
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
