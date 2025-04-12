package com.captionit.screen.home.viewModel

import androidx.lifecycle.ViewModel
import com.captionit.components.Post
import com.captionit.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _posts = MutableStateFlow(
        List(10) { index -> Post(id = index, username = "User$index") }
    )
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _isDrawerOpen = MutableStateFlow(false)
    val isDrawerOpen: StateFlow<Boolean> = _isDrawerOpen.asStateFlow()

    private val _selectedDrawerItem = MutableStateFlow<String?>(null)
    val selectedDrawerItem: StateFlow<String?> = _selectedDrawerItem.asStateFlow()

    fun onDrawerItemClick(label: String, navigateTo: (String) -> Unit) {
        _selectedDrawerItem.value = label
        _isDrawerOpen.value = false

        val route = when (label.lowercase()) {
            "upload" -> Screen.POST.name
            "search" -> Screen.SEARCH.name
            "favorites" -> Screen.FAVORITES.name
            "settings" -> Screen.SETTINGS.name
            else -> Screen.HOME.name
        }

        navigateTo(route)
    }


    fun onCaptionChange(postId: Int, newCaption: String) {
        _posts.update { posts ->
            posts.map { if (it.id == postId) it.copy(caption = newCaption) else it }
        }
    }

    fun onCommentClick(postId: Int) {
        println("Comment clicked on post $postId")
    }

    fun toggleDrawer(open: Boolean) {
        _isDrawerOpen.value = open
    }

    fun refreshPosts() {
        _posts.value = List(10) { index ->
            Post(id = index, username = "User$index")
        }
    }

}
