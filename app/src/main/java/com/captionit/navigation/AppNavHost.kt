package com.captionit.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.captionit.screen.favorites.view.FavoritesScreen
import com.captionit.screen.home.view.HomeScreen
import com.captionit.screen.login.view.LoginScreen
import com.captionit.screen.post.view.PostScreen
import com.captionit.screen.profile.view.ProfileScreen
import com.captionit.screen.search.view.SearchScreen
import com.captionit.screen.settings.view.SettingsScreen

@Composable
fun AppNavHost(
    context: Context,
    modifier: Modifier = Modifier,
    navController: NavHostController = NavHostController(
        context = context
    ),
    startDestination: String = NavigationItem.Login.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(navController)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationItem.Post.route) {
            PostScreen(navController)
        }
        composable(NavigationItem.Search.route) {
            SearchScreen(navController)
        }
        composable(NavigationItem.Favorites.route) {
            FavoritesScreen(navController)
        }
        composable(NavigationItem.Settings.route) {
            SettingsScreen(navController)
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen(navController)
        }
    }
}