package com.captionit.screen.home.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.captionit.components.PostCard
import com.captionit.components.scaffold.CaptionItScaffold
import com.captionit.screen.home.viewModel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val posts by viewModel.posts.collectAsState()

    CaptionItScaffold(
        navController = navController,
        onRefresh = { viewModel.refreshPosts() }
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
}
