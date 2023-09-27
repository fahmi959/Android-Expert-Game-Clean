package com.fahmi.androidexpertgame.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fahmi.androidexpertgame.core.R
import com.fahmi.androidexpertgame.core.databinding.ItemGameBinding
import com.fahmi.androidexpertgame.core.domain.model.Game
import java.util.ArrayList

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null

    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGameBinding.bind(itemView)
        fun bind(data: Game) {
            with(binding) {
//                Glide.with(itemView.context)
//                    .load(data.backgroundImage)
//                    .fitCenter()
//                    .centerCrop()
//                    .into(backgroundImage)
               name.text = data.name
               rating.text = data.rating.toString()
                released.text = data.released
            }

            if (data.rating > 3) {
                binding.rating.setTextColor(ContextCompat.getColor(itemView.context, R.color.rating_meh))
            } else {
                binding.rating.setTextColor(ContextCompat.getColor(itemView.context, R.color.rating_skip))
            }
        }





        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}