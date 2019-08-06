package me.tumur.portfolio.utils.adapters.listItemAdapters.experience

import me.tumur.portfolio.repository.database.model.experience.ExperienceModel

class ExperienceClickListener(val clickListener: (item: ExperienceModel) -> Unit) {
    fun onClick(item: ExperienceModel) = clickListener(item)
}