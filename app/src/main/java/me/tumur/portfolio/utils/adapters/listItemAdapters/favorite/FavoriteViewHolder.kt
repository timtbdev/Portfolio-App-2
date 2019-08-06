package me.tumur.portfolio.utils.adapters.listItemAdapters.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemFavoriteBinding
import me.tumur.portfolio.repository.database.model.favorite.FavoriteModel

/**
 * Favorite item viewholder
 * */
class FavoriteViewHolder private constructor(val binding: ListItemFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FavoriteModel?, clickListener: FavoriteClickListener) {
        binding.clickListener = clickListener
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): FavoriteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemFavoriteBinding.inflate(layoutInflater, parent, false)
            return FavoriteViewHolder(binding)
        }
    }
}