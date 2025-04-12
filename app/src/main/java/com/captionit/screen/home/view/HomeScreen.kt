package com.captionit.screen.home.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.captionit.components.CaptionItTopBar
import com.captionit.components.DrawerContent
import com.captionit.components.PostCard
import com.captionit.navigation.Screen
import com.captionit.screen.home.viewModel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val posts by viewModel.posts.collectAsState()
    val isDrawerOpen by viewModel.isDrawerOpen.collectAsState()

    val drawerState = rememberDrawerState(
        initialValue = if (isDrawerOpen) DrawerValue.Open else DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    val selectedDrawerItem by viewModel.selectedDrawerItem.collectAsState()

    LaunchedEffect(drawerState.isOpen) {
        viewModel.toggleDrawer(drawerState.isOpen)
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                selectedItem = selectedDrawerItem,
                onItemClick = { label -> viewModel.onDrawerItemClick(label, navController::navigate) }
            )

        },
        content = {

        Scaffold(
            topBar = {
                CaptionItTopBar(
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onProfileClick = { navController.navigate(Screen.PROFILE.name) },
                    onTitleClick = {
                        if (navController.currentDestination?.route == Screen.HOME.name) {
                            viewModel.refreshPosts()
                        } else {
                            navController.navigate(Screen.HOME.name) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )

            }
        ) { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(posts) { post ->
                    PostCard(
                        post = post,
                        onCaptionChange = { new -> viewModel.onCaptionChange(post.id, new) },
                        onCommentClick = { viewModel.onCommentClick(post.id) }
                    )
                }
            }
        }
    })
}
