package com.captionit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChangeHistory
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PostCard(
    post: Post,
    onCaptionChange: (String) -> Unit,
    onCommentClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6D5F7)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = post.username, fontWeight = FontWeight.Bold)
                    Text(text = "@${post.username}", style = MaterialTheme.typography.labelSmall)
                }
                Spacer(Modifier.weight(1f))
                Icon(Icons.Default.StarBorder, contentDescription = "Favorite")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Icon(Icons.Default.ChangeHistory, contentDescription = "Triangle", modifier = Modifier.size(40.dp))
                    Icon(Icons.Default.Stop, contentDescription = "Square", modifier = Modifier.size(40.dp))
                    Icon(Icons.Default.Circle, contentDescription = "Circle", modifier = Modifier.size(40.dp))
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text("Caption", style = MaterialTheme.typography.labelSmall)
                OutlinedTextField(
                    value = post.caption,
                    onValueChange = onCaptionChange,
                    placeholder = { Text("Input") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onCommentClick) {
                    Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Comment")
                }
            }
        }
    }
}
