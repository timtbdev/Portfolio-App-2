package me.tumur.portfolio.utils.adapters.listItemAdapters.experience

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemExperienceBinding
import me.tumur.portfolio.repository.database.model.experience.ExperienceModel

/**
 * Experience item viewholder
 * */
class ExperienceViewHolder private constructor(val binding: ListItemExperienceBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ExperienceModel?, clickListener: ExperienceClickListener) {
        binding.clickListener = clickListener
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ExperienceViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemExperienceBinding.inflate(layoutInflater, parent, false)
            return ExperienceViewHolder(binding)
        }
    }
}