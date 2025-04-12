package com.captionit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    selectedItem: String?,
    onItemClick: (String) -> Unit
) {
    val items = listOf("Upload", "Search", "Favorites", "Settings")
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .background(Color(0xFFF7ECFF))
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.forEach { label ->
            DrawerButton(
                label = label,
                icon = when (label) {
                    "Upload" -> Icons.Default.Upload
                    "Search" -> Icons.Default.Search
                    "Favorites" -> Icons.Default.StarBorder
                    "Settings" -> Icons.Default.Settings
                    else -> Icons.Default.Info
                },
                isSelected = selectedItem == label,
                onItemClick = onItemClick
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
