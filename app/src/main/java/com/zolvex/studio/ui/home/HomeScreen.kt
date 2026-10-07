package com.zolvex.studio.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zolvex.studio.data.model.Template
import com.zolvex.studio.ui.components.TemplateCard
import com.zolvex.studio.ui.theme.ZAccent
import com.zolvex.studio.ui.theme.ZAccentSoft
import com.zolvex.studio.ui.theme.ZBorder
import com.zolvex.studio.ui.theme.ZSurface
import com.zolvex.studio.ui.theme.ZTextSecondary

private data class QuickCreateItem(val label: String, val size: String, val icon: ImageVector)

private val quickCreateItems = listOf(
    QuickCreateItem("Blank Canvas", "Custom", Icons.Outlined.Add),
    QuickCreateItem("Instagram Post", "1080 x 1080", Icons.Outlined.PhotoLibrary),
    QuickCreateItem("Instagram Story", "1080 x 1920", Icons.Outlined.PhoneAndroid),
    QuickCreateItem("YouTube Thumbnail", "1280 x 720", Icons.Outlined.PlayCircle),
    QuickCreateItem("WhatsApp Status", "1080 x 1920", Icons.Outlined.ChatBubbleOutline),
    QuickCreateItem("Facebook Post", "1200 x 630", Icons.Outlined.ThumbUp),
    QuickCreateItem("Flyer", "A4", Icons.Outlined.Description),
    QuickCreateItem("Poster", "18 x 24 in", Icons.Outlined.Brush),
    QuickCreateItem("Photo Edit", "Original", Icons.Outlined.Tune)
)

@Composable
fun HomeScreen(
    trendingTemplates: List<Template>,
    favorites: Set<String>,
    onExploreTemplates: () -> Unit,
    onCreateDesign: () -> Unit,
    onProfileClick: () -> Unit,
    onTemplateClick: (Template) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "ZOLVEX",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 4.sp
                    )
                    Text(text = "Studio", style = MaterialTheme.typography.bodyMedium, color = ZAccentSoft)
                }
                Row {
                    IconButton(onClick = onExploreTemplates) {
                        Icon(Icons.Outlined.Search, contentDescription = "Search templates")
                    }
                    IconButton(onClick = onProfileClick) {
                        Icon(Icons.Outlined.Person, contentDescription = "Profile")
                    }
                }
            }
        }

        item {
            Column(Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Create something extraordinary.",
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = onExploreTemplates, shape = RoundedCornerShape(14.dp)) {
                        Text("Explore Templates")
                    }
                    OutlinedButton(
                        onClick = onCreateDesign,
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, ZBorder)
                    ) {
                        Text("Create Design")
                    }
                }
            }
        }

        item {
            Section(title = "Continue Editing") {
                Surface(
                    onClick = onCreateDesign,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = ZSurface,
                    border = BorderStroke(1.dp, ZBorder)
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Text("No recent projects yet", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Start a design and it will show up here.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = ZTextSecondary
                        )
                    }
                }
            }
        }

        item {
            Section(title = "Quick Create", padded = false) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(quickCreateItems) { item ->
                        Surface(
                            onClick = onCreateDesign,
                            modifier = Modifier.width(120.dp),
                            shape = RoundedCornerShape(16.dp),
                            color = ZSurface,
                            border = BorderStroke(1.dp, ZBorder)
                        ) {
                            Column(Modifier.padding(14.dp)) {
                                Icon(item.icon, contentDescription = null, tint = ZAccent)
                                Spacer(Modifier.height(14.dp))
                                Text(
                                    item.label,
                                    style = MaterialTheme.typography.labelLarge,
                                    minLines = 2,
                                    maxLines = 2
                                )
                                Text(
                                    item.size,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = ZTextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Section(title = "Trending Templates", padded = false) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(trendingTemplates, key = { it.id }) { template ->
                        TemplateCard(
                            template = template,
                            isFavorite = template.id in favorites,
                            onClick = { onTemplateClick(template) },
                            onFavoriteClick = { onToggleFavorite(template.id) },
                            modifier = Modifier.width(160.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Section(
    title: String,
    padded: Boolean = true,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.height(12.dp))
        if (padded) {
            Column(Modifier.padding(horizontal = 20.dp)) { content() }
        } else {
            content()
        }
    }
}
