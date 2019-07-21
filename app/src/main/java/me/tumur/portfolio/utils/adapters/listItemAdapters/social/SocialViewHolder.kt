package me.tumur.portfolio.utils.adapters.listItemAdapters.social

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemBsSocialBinding
import me.tumur.portfolio.repository.database.model.profile.SocialModel

/**
 * Constructor of ViewHolder
 * */
class SocialViewHolder private constructor(val binding: ListItemBsSocialBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(socialItem: SocialModel, clickListener: SocialClickListener){
        binding.clickListener = clickListener
        binding.social = socialItem
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): SocialViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBsSocialBinding.inflate(layoutInflater, parent, false)
            return SocialViewHolder(
                binding
            )
        }
    }
}