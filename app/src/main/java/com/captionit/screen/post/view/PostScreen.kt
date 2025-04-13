package com.captionit.screen.post.view

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.captionit.navigation.Screen
import com.captionit.screen.post.viewModel.PostViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.captionit.components.scaffold.CaptionItScaffold

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PostScreen(
    navController: NavHostController,
    viewModel: PostViewModel = hiltViewModel()
) {
    val imageUri by viewModel.imageUri.collectAsState()

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.onImageSelected(uri)
    }

    CaptionItScaffold(navController = navController, titleClickRoute = Screen.HOME.name) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    GlideImage(
                        model = imageUri,
                        contentDescription = "Selected Image",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "Placeholder",
                        tint = Color.Gray,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(160.dp))

            Button(
                onClick = { launcher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2E9FF)),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Upload, contentDescription = "Upload")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Upload Photo", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.onPost(context) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2E9FF)),
                elevation = ButtonDefaults.buttonElevation(8.dp),
                modifier = Modifier.fillMaxWidth(),
                enabled = imageUri != null
            ) {
                Icon(Icons.Default.Check, contentDescription = "Post")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Post")
            }
        }
    }
}

