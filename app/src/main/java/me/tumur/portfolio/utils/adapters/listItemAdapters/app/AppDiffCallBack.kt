package me.tumur.portfolio.utils.adapters.listItemAdapters.app

import androidx.recyclerview.widget.DiffUtil
import me.tumur.portfolio.repository.database.model.settings.AppModel

class AppDiffCallBack: DiffUtil.ItemCallback<AppModel>() {
    override fun areItemsTheSame(oldItem: AppModel, newItem: AppModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: AppModel, newItem: AppModel): Boolean {
        return oldItem == newItem
    }

}