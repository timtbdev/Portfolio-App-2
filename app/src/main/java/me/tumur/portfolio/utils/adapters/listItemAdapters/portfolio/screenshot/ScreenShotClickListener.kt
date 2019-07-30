package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.screenshot

class ScreenShotClickListener(val clickListener: (url: String) -> Unit) {
    fun onClick(url: String) = clickListener(url)
}