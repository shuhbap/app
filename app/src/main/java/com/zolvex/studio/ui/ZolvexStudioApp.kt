package com.zolvex.studio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zolvex.studio.data.model.Project
import com.zolvex.studio.ui.editor.EditorScreen
import com.zolvex.studio.ui.home.HomeScreen
import com.zolvex.studio.ui.navigation.Screen
import com.zolvex.studio.ui.navigation.isBottomBarRoute
import com.zolvex.studio.ui.profile.ProfileScreen
import com.zolvex.studio.ui.projects.ProjectsScreen
import com.zolvex.studio.ui.splash.SplashScreen
import com.zolvex.studio.ui.templates.TemplatesScreen
import com.zolvex.studio.ui.templates.TemplatesViewModel
import com.zolvex.studio.ui.theme.ZAccent
import com.zolvex.studio.ui.theme.ZAccentSoft
import com.zolvex.studio.ui.theme.ZBackground
import com.zolvex.studio.ui.theme.ZBorder
import com.zolvex.studio.ui.theme.ZSurface
import com.zolvex.studio.ui.theme.ZTextSecondary

private fun NavController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(Screen.Home.route) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun ZolvexStudioApp(viewModel: TemplatesViewModel = viewModel()) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val projects = emptyList<Project>()

    Scaffold(
        containerColor = ZBackground,
        bottomBar = {
            if (isBottomBarRoute(currentRoute)) {
                ZolvexBottomBar(
                    currentRoute = currentRoute,
                    onTabClick = { navController.navigateToTab(it) },
                    onCreateClick = { navController.navigate(Screen.Editor.create(Screen.Editor.BLANK)) }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(onFinished = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    trendingTemplates = viewModel.allTemplates.take(6),
                    favorites = viewModel.favorites,
                    onExploreTemplates = { navController.navigateToTab(Screen.Templates.route) },
                    onCreateDesign = { navController.navigate(Screen.Editor.create(Screen.Editor.BLANK)) },
                    onProfileClick = { navController.navigateToTab(Screen.Profile.route) },
                    onTemplateClick = { navController.navigate(Screen.Editor.create(it.id)) },
                    onToggleFavorite = { viewModel.toggleFavorite(it) }
                )
            }
            composable(Screen.Templates.route) {
                TemplatesScreen(
                    viewModel = viewModel,
                    onTemplateClick = { navController.navigate(Screen.Editor.create(it.id)) }
                )
            }
            composable(Screen.Projects.route) {
                ProjectsScreen(
                    projects = projects,
                    onCreateDesign = { navController.navigate(Screen.Editor.create(Screen.Editor.BLANK)) }
                )
            }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(
                route = Screen.Editor.route,
                arguments = listOf(navArgument(Screen.Editor.ARG_TEMPLATE_ID) { type = NavType.StringType })
            ) { entry ->
                val id = entry.arguments?.getString(Screen.Editor.ARG_TEMPLATE_ID)
                EditorScreen(
                    template = id?.let { viewModel.templateById(it) },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun ZolvexBottomBar(
    currentRoute: String?,
    onTabClick: (String) -> Unit,
    onCreateClick: () -> Unit
) {
    Column(
        Modifier
            .background(ZSurface)
            .navigationBarsPadding()
    ) {
        HorizontalDivider(color = ZBorder)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BarItem("Home", Icons.Outlined.Home, currentRoute == Screen.Home.route, Modifier.weight(1f)) {
                onTabClick(Screen.Home.route)
            }
            BarItem("Templates", Icons.Outlined.GridView, currentRoute == Screen.Templates.route, Modifier.weight(1f)) {
                onTabClick(Screen.Templates.route)
            }
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(ZAccent, ZAccentSoft)))
                        .clickable(onClick = onCreateClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.Add, contentDescription = "Create design", tint = Color.White)
                }
            }
            BarItem("Projects", Icons.Outlined.FolderOpen, currentRoute == Screen.Projects.route, Modifier.weight(1f)) {
                onTabClick(Screen.Projects.route)
            }
            BarItem("Profile", Icons.Outlined.Person, currentRoute == Screen.Profile.route, Modifier.weight(1f)) {
                onTabClick(Screen.Profile.route)
            }
        }
    }
}

@Composable
private fun BarItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val tint = if (selected) ZAccentSoft else ZTextSecondary
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = label, tint = tint)
        Text(label, color = tint, fontSize = 11.sp)
    }
}
