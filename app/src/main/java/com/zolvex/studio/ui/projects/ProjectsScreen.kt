package com.zolvex.studio.ui.projects

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zolvex.studio.data.model.Project
import com.zolvex.studio.ui.theme.ZBorder
import com.zolvex.studio.ui.theme.ZSurface
import com.zolvex.studio.ui.theme.ZTextSecondary

@Composable
fun ProjectsScreen(
    projects: List<Project>,
    onCreateDesign: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Projects",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 12.dp)
        )
        if (projects.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Outlined.FolderOpen,
                        contentDescription = null,
                        tint = ZTextSecondary
                    )
                    Spacer(Modifier.height(12.dp))
                    Text("No projects yet", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Your designs will appear here.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = ZTextSecondary
                    )
                    Spacer(Modifier.height(20.dp))
                    Button(onClick = onCreateDesign, shape = RoundedCornerShape(14.dp)) {
                        Text("Create Design")
                    }
                }
            }
        } else {
            LazyColumn(
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(projects, key = { it.id }) { project ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = ZSurface,
                        border = BorderStroke(1.dp, ZBorder)
                    ) {
                        Text(project.name, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
