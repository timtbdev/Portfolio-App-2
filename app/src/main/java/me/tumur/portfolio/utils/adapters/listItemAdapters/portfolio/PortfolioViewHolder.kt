package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemPortfolioBinding
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel

/**
 * Portfolio item viewholder
 * */
class PortfolioViewHolder private constructor(val binding: ListItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: PortfolioModel?, clickListener: PortfolioClickListener){
        binding.clickListener = clickListener
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): PortfolioViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemPortfolioBinding.inflate(layoutInflater, parent, false)
            return PortfolioViewHolder(binding)
        }
    }
}