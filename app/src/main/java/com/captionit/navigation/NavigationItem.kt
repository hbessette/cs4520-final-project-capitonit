package com.captionit.navigation

sealed class NavigationItem(val route: String) {
    object Login: NavigationItem(Screen.LOGIN.name)
    object Home: NavigationItem(Screen.HOME.name)
    object Favorites: NavigationItem(Screen.FAVORITES.name)
    object Post: NavigationItem(Screen.POST.name)
    object Search: NavigationItem(Screen.SEARCH.name)
    object Settings: NavigationItem(Screen.SETTINGS.name)
    object Profile: NavigationItem(Screen.PROFILE.name)
}