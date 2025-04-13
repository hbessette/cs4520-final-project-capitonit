package com.captionit.components.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.captionit.components.CaptionItTopBar
import com.captionit.components.DrawerContent
import com.captionit.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun CaptionItScaffold(
    navController: NavHostController,
    drawerViewModel: DrawerViewModel = hiltViewModel(), // now uses new VM
    titleClickRoute: String = Screen.HOME.name,
    onRefresh: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    val isDrawerOpen by drawerViewModel.isDrawerOpen.collectAsState()
    val drawerState = rememberDrawerState(
        initialValue = if (isDrawerOpen) DrawerValue.Open else DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()
    val selectedDrawerItem by drawerViewModel.selectedDrawerItem.collectAsState()

    LaunchedEffect(drawerState.isOpen) {
        drawerViewModel.toggleDrawer(drawerState.isOpen)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                selectedItem = selectedDrawerItem,
                onItemClick = { label ->
                    drawerViewModel.onDrawerItemClick(label) { route ->
                        navController.navigate(route)
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                CaptionItTopBar(
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onProfileClick = { navController.navigate(Screen.PROFILE.name) },
                    onTitleClick = {
                        if (navController.currentDestination?.route == titleClickRoute) {
                            onRefresh?.invoke()
                        } else {
                            navController.navigate(titleClickRoute) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            },
            content = content
        )
    }
}
