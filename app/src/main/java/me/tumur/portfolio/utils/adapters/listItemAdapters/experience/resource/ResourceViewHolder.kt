package me.tumur.portfolio.utils.adapters.listItemAdapters.experience.resource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemResourceBinding
import me.tumur.portfolio.repository.database.model.resource.ResourceModel
import me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button.ButtonClickListener

/**
 * Resource item viewholder
 * */
class ResourceViewHolder private constructor(val binding: ListItemResourceBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ResourceModel?, clickListener: ButtonClickListener) {
        binding.clickListener = clickListener
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ResourceViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemResourceBinding.inflate(layoutInflater, parent, false)
            return ResourceViewHolder(binding)
        }
    }
}