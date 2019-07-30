package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemButtonBinding
import me.tumur.portfolio.repository.database.model.button.ButtonModel

/**
 * Button normal viewholder
 * */
class ButtonNormalViewHolder private constructor(val binding: ListItemButtonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ButtonModel, clickListener: ButtonClickListener) {
        binding.clickListener = clickListener
        binding.button = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ButtonNormalViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemButtonBinding.inflate(layoutInflater, parent, false)
            return ButtonNormalViewHolder(binding)
        }
    }
}