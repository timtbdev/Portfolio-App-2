package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.databinding.ListItemCategoryBinding
import me.tumur.portfolio.repository.database.model.category.CategoryModel

/**
 * Category viewholder
 * */
class CategoryViewHolder private constructor(val binding: ListItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CategoryModel?) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CategoryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemCategoryBinding.inflate(layoutInflater, parent, false)
            return CategoryViewHolder(binding)
        }
    }
}