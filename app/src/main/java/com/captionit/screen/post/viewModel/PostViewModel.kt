package com.captionit.screen.post.viewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor() : ViewModel() {

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> = _imageUri.asStateFlow()

    fun onImageSelected(uri: Uri?) {
        _imageUri.value = uri
    }

    fun onPost(context: Context) {
        _imageUri.value?.let {
            Toast.makeText(context, "Posted successfully!", Toast.LENGTH_SHORT).show()
            _imageUri.value = null
        } ?: Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
    }
}
