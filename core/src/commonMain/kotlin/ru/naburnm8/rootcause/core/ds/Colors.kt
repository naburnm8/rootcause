package ru.naburnm8.rootcause.core.ds

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val background = Color(0xFF171D27)
val surface = Color(0xFF242E3D)
val elevated = Color(0xFF304052)
val accent = Color(0xFF75A9EB)
val success = Color(0xFF53BA94)
val warning = Color(0xFFE0A45D)
val error = Color(0xFFE47C83)
val text = Color(0xFFF0F4FA)
val muted = Color(0xFFA9B7CB)
val border = Color(0xFF405068)

val rootCauseColorScheme = darkColorScheme(
    primary = accent,
    background = background,
    surface = surface,
    onSurface = text,
    onBackground = text,
    secondary = muted,
)