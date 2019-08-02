package me.tumur.portfolio.screens.portfolio.detail

class VideoClickListener(val clickListener: (url: String) -> Unit) {
    fun onClick(url: String) = clickListener(url)
}