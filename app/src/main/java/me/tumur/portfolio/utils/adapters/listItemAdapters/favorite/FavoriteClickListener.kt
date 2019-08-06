package me.tumur.portfolio.utils.adapters.listItemAdapters.favorite

import me.tumur.portfolio.repository.database.model.favorite.FavoriteModel

class FavoriteClickListener(val clickListener: (item: FavoriteModel, delete: Boolean) -> Unit) {
    fun onClick(item: FavoriteModel, delete: Boolean) = clickListener(item, delete)
}