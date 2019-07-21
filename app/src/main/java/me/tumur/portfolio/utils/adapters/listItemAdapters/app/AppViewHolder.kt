package me.tumur.portfolio.utils.adapters.listItemAdapters.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemDialogAppBinding
import me.tumur.portfolio.repository.database.model.settings.AppModel

/**
 * Constructor of ViewHolder
 * */
class AppViewHolder private constructor(val binding: ListItemDialogAppBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(appInfoItem: AppModel){
        binding.model = appInfoItem
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): AppViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemDialogAppBinding.inflate(layoutInflater, parent, false)
            return AppViewHolder(binding)
        }
    }
}