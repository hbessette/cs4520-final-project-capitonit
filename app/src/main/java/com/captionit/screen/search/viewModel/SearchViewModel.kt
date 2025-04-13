package com.captionit.screen.search.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Profile(
    val id: Int,
    val username: String,
    val imageUrl: String,
    val isFollowing: Boolean
)

class SearchViewModel : ViewModel() {

    private val allProfiles = List(20) {
        Profile(
            id = it,
            username = "User$it",
            imageUrl = "https://via.placeholder.com/150/AA${it}FF/FFFFFF?Text=U${it}",
            isFollowing = it % 2 == 0
        )
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filteredProfiles = MutableStateFlow(allProfiles)
    val filteredProfiles: StateFlow<List<Profile>> = _filteredProfiles.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _filteredProfiles.value = allProfiles.filter {
            it.username.contains(query, ignoreCase = true)
        }
    }

    fun onFollowToggle(profileId: Int) {
        _filteredProfiles.update { profiles ->
            profiles.map {
                if (it.id == profileId) it.copy(isFollowing = !it.isFollowing) else it
            }
        }
    }
}
