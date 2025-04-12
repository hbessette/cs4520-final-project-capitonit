package com.captionit.screen.home.viewModel

import androidx.lifecycle.ViewModel
import com.captionit.components.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    // Fake data for posts
    private val _posts = MutableStateFlow(
        List(10) { index ->
            Post(id = index, username = "User$index")
        }
    )
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    fun onCaptionChange(postId: Int, newCaption: String) {
        _posts.update { posts ->
            posts.map { post ->
                if (post.id == postId) post.copy(caption = newCaption) else post
            }
        }
    }

    fun onCommentClick(postId: Int) {
        println("Comment clicked on post $postId")
    }
}
