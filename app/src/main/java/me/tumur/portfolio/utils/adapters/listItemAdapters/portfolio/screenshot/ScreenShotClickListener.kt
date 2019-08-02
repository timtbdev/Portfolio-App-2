package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.screenshot

class ScreenShotClickListener(val clickListener: (id: String, order: Int) -> Unit) {
    fun onClick(id: String, order: Int) = clickListener(id, order)
}