package me.tumur.portfolio.utils.adapters.listItemAdapters.social

import me.tumur.portfolio.repository.database.model.profile.SocialModel

class SocialClickListener(val clickListener: (socialItem: SocialModel) -> Unit){
    fun onClick(item: SocialModel) = clickListener(item)
}