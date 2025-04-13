package com.captionit.components.scaffold

import androidx.lifecycle.ViewModel
import com.captionit.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor() : ViewModel() {

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

    fun toggleDrawer(open: Boolean) {
        _isDrawerOpen.value = open
    }
}
