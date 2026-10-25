package ru.naburnm8.rootcause.feature.omnitracker.presentation.tab

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

enum class TrackerTab {
    ISSUES, SKILLS, HISTORY, PROFILE
}

@Composable
fun TrackerTab.text(): String {
    return when (this) {
        TrackerTab.ISSUES -> "Issues"
        TrackerTab.SKILLS -> "Skills"
        TrackerTab.HISTORY -> "History"
        TrackerTab.PROFILE -> "Profile"
    }
}

@Composable
fun TrackerTab.ComposableIcon() {
    when (this) {
        TrackerTab.ISSUES -> Text("▤", fontSize = 23.sp)
        TrackerTab.SKILLS -> Text("◇", fontSize = 23.sp)
        TrackerTab.HISTORY -> Text("◷", fontSize = 23.sp)
        TrackerTab.PROFILE -> Text("◎", fontSize = 23.sp)
    }
}