package com.zolvex.studio.ui.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zolvex.studio.data.model.Template
import com.zolvex.studio.ui.components.TemplatePreview
import com.zolvex.studio.ui.theme.ZSurface
import com.zolvex.studio.ui.theme.ZTextSecondary

/** Phase 1 placeholder. The real canvas engine arrives in Phase 2. */
@Composable
fun EditorScreen(template: Template?, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = template?.name ?: "Untitled design",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(ZSurface)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            if (template != null) {
                TemplatePreview(template = template)
            } else {
                Text("Blank canvas", color = ZTextSecondary)
            }
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = if (template != null) "${template.layers.size} editable layers. Editor engine arrives in Phase 2."
            else "Editor engine arrives in Phase 2.",
            color = ZTextSecondary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        )
    }
}
