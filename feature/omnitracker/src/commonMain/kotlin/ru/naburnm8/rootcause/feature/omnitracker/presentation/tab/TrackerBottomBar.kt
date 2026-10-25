package ru.naburnm8.rootcause.feature.omnitracker.presentation.tab

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TrackerBottomBar(
    selected: TrackerTab,
    onTabClick: (TrackerTab) -> Unit
) {
    NavigationBar(tonalElevation = 0.dp) {
        TrackerTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = selected == tab,
                onClick = { onTabClick(tab) },
                icon = { tab.ComposableIcon() },
                label = { Text(tab.text()) },
            )
        }
    }
}