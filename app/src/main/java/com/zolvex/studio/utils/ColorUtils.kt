package com.zolvex.studio.utils

import androidx.compose.ui.graphics.Color

/** Parses "#RRGGBB" / "#AARRGGBB". Falls back to gray so a bad template never crashes. */
fun parseHexColor(hex: String): Color =
    runCatching { Color(android.graphics.Color.parseColor(hex)) }.getOrDefault(Color.Gray)
