package ru.naburnm8.rootcause.core.ds

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun RootCauseTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = rootCauseColorScheme, content = content)
}