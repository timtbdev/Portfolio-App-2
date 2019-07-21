package me.tumur.portfolio.utils.adapters.listItemAdapters.social

import androidx.recyclerview.widget.DiffUtil
import me.tumur.portfolio.repository.database.model.profile.SocialModel

class SocialDiffCallBack: DiffUtil.ItemCallback<SocialModel>() {
    override fun areItemsTheSame(oldItem: SocialModel, newItem: SocialModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: SocialModel, newItem: SocialModel): Boolean {
        return oldItem == newItem
    }

}