package me.tumur.portfolio.utils.state

sealed class NavigationState

/** Hide Navigation */
object HideNavigation: NavigationState()

/** Show Navigation */
object ShowNavigation : NavigationState()

/** Hide Bottom navigation */
object GoneNavigation : NavigationState()