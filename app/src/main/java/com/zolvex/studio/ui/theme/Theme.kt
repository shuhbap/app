package com.zolvex.studio.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ZolvexColorScheme = darkColorScheme(
    primary = ZAccent,
    onPrimary = Color.White,
    secondary = ZAccentSoft,
    onSecondary = Color.Black,
    background = ZBackground,
    onBackground = ZTextPrimary,
    surface = ZSurface,
    onSurface = ZTextPrimary,
    surfaceVariant = ZSurface2,
    onSurfaceVariant = ZTextSecondary,
    outline = ZBorder,
    outlineVariant = ZBorder
)

@Composable
fun ZolvexStudioTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ZolvexColorScheme,
        typography = ZolvexTypography,
        content = content
    )
}
