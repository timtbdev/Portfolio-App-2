package me.tumur.portfolio.utils.state

sealed class NavigationState

/** Hide Navigation */
object HideNavigation: NavigationState()

/** Welcome Screen */
object ShowNavigation : NavigationState()