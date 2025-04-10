package captionit.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import captionit.screen.home.view.HomeScreen
import captionit.screen.login.view.LoginScreen

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
    }
}