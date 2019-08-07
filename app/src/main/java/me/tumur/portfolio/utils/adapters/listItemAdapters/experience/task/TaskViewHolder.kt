package me.tumur.portfolio.utils.adapters.listItemAdapters.experience.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemTaskBinding
import me.tumur.portfolio.repository.database.model.task.TaskModel

/**
 * Task viewholder
 * */
class TaskViewHolder private constructor(val binding: ListItemTaskBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TaskModel?) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): TaskViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemTaskBinding.inflate(layoutInflater, parent, false)
            return TaskViewHolder(binding)
        }
    }
}