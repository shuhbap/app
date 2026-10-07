package com.zolvex.studio.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object Templates : Screen("templates")
    data object Projects : Screen("projects")
    data object Profile : Screen("profile")
    data object Editor : Screen("editor/{templateId}") {
        const val ARG_TEMPLATE_ID = "templateId"
        fun create(templateId: String) = "editor/$templateId"
        const val BLANK = "blank"
    }
}

fun isBottomBarRoute(route: String?): Boolean = when (route) {
    Screen.Home.route, Screen.Templates.route, Screen.Projects.route, Screen.Profile.route -> true
    else -> false
}
