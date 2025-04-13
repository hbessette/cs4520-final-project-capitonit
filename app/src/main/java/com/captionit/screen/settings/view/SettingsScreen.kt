package com.captionit.screen.settings.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.captionit.components.SettingSwitch
import com.captionit.components.scaffold.CaptionItScaffold
import com.captionit.navigation.Screen
import com.captionit.screen.settings.viewModel.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavHostController
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val isDarkTheme by viewModel.isDarkMode.collectAsState()
    val isNotificationsEnabled by viewModel.isNotificationsEnabled.collectAsState()
    val isPrivateModeEnabled by viewModel.isPrivateModeEnabled.collectAsState()

    CaptionItScaffold(
        navController = navController,
        titleClickRoute = Screen.HOME.name
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }

            item {
                SettingSwitch(
                    label = "Dark Mode",
                    checked = isDarkTheme,
                    onCheckedChange = viewModel::toggleDarkMode
                )
            }

            item {
                SettingSwitch(
                    label = "Notifications",
                    checked = isNotificationsEnabled,
                    onCheckedChange = viewModel::toggleNotifications
                )
            }
            item {
                SettingSwitch(
                    label = "Private Mode",
                    checked = isPrivateModeEnabled,
                    onCheckedChange = viewModel::togglePrivateMode
                )
            }
        }
    }
}


