package com.captionit.screen.search.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.captionit.R
import com.captionit.components.ProfileListItem
import com.captionit.components.scaffold.CaptionItScaffold
import com.captionit.navigation.Screen
import com.captionit.screen.search.viewModel.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.searchQuery.collectAsState()
    val profiles by viewModel.filteredProfiles.collectAsState()

    CaptionItScaffold(navController = navController, titleClickRoute = Screen.HOME.name) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Surface(
                color = Color(0xFFF8F0FF),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BasicTextField(
                        value = query,
                        onValueChange = viewModel::onSearchQueryChanged,
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            if (query.isEmpty()) {
                                Text("Search", color = Color.Gray)
                            }
                            innerTextField()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(profiles) { profile ->
                    ProfileListItem(
                        username = profile.username,
                        imageUrl = profile.imageUrl,
                        isFollowing = profile.isFollowing,
                        onCheckedChange = { viewModel.onFollowToggle(profile.id) }
                    )
                }
            }
        }
    }
}
