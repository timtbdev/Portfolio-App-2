package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.screenshot

import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel

class ScreenShotClickListener(val clickListener: (model: ScreenShotModel) -> Unit) {
    fun onClick(model: ScreenShotModel) = clickListener(model)
}