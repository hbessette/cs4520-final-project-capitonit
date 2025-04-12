@file:JvmName("CaptionItAppKt")

package com.captionit.screen

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.captionit.navigation.AppNavHost

@Composable
fun CaptionItAppComp(context : Context) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            AppNavHost(
                navController = rememberNavController(),
                context = context,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}