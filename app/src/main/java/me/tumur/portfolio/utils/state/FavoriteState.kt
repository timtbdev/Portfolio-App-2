package me.tumur.portfolio.utils.state

sealed class FavoriteState

/** Empty */
object Empty : FavoriteState()

/** Not Empty */
object NotEmpty : FavoriteState()