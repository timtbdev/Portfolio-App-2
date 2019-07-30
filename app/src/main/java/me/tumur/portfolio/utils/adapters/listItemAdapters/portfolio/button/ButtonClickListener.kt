package me.tumur.portfolio.utils.adapters.listItemAdapters.portfolio.button

class ButtonClickListener(val clickListener: (url: String) -> Unit) {
    fun onClick(url: String) = clickListener(url)
}