package ru.naburnm8.rootcause.feature.omnitracker.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naburnm8.rootcause.core.ds.accent
import ru.naburnm8.rootcause.core.ds.background
import ru.naburnm8.rootcause.core.ds.border
import ru.naburnm8.rootcause.core.ds.elevated
import ru.naburnm8.rootcause.core.ds.muted
import ru.naburnm8.rootcause.core.ds.surface
import ru.naburnm8.rootcause.feature.omnitracker.model.IssueStatus
import ru.naburnm8.rootcause.feature.omnitracker.presentation.tab.TrackerBottomBar
import ru.naburnm8.rootcause.feature.omnitracker.presentation.tab.TrackerTab
import ru.naburnm8.rootcause.feature.omnitracker.presentation.uimodel.DeveloperUiModel
import ru.naburnm8.rootcause.feature.omnitracker.presentation.uimodel.IssueUiModel

@Composable
fun OmniTrackerScreen(
    issues: List<IssueUiModel>,
    developer: DeveloperUiModel,
    modifier: Modifier = Modifier,
    onIssueClick: (IssueUiModel) -> Unit = {},
    onTabClick: (TrackerTab) -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf<IssueStatus?>(null) }
    val visibleIssues = remember(issues, selectedFilter) {
        issues.filter { selectedFilter == null || it.status == selectedFilter }
    }

    Scaffold(
        modifier = modifier,
        containerColor = background,
        bottomBar = { TrackerBottomBar(selected = TrackerTab.ISSUES, onTabClick = onTabClick) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item { TrackerHeader() }
            item { DeveloperProgress(developer) }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Мои задачи", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text("Назначены на меня · Sprint 01", color = muted, fontSize = 12.sp)
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    FilterPill("All", selectedFilter == null) { selectedFilter = null }
                    IssueStatus.entries.forEach { status ->
                        FilterPill(status.title, selectedFilter == status) { selectedFilter = status }
                    }
                }
            }
            if (visibleIssues.isEmpty()) {
                item { Text("No issues with this status", color = muted) }
            } else {
                items(visibleIssues, key = { it.id }) { issue ->
                    IssueCard(issue = issue, onClick = { onIssueClick(issue) })
                }
            }
        }
    }
}

@Composable
private fun TrackerHeader() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.size(42.dp).background(elevated, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) { Text("O", fontWeight = FontWeight.ExtraBold, color = accent, fontSize = 25.sp) }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text("Omni Tracker", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Omni Tech · Mobile Team", color = muted, fontSize = 12.sp)
        }
        Text("●", color = accent, fontSize = 14.sp)
    }
}

@Composable
private fun DeveloperProgress(developer: DeveloperUiModel) {
    Card(
        colors = CardDefaults.cardColors(containerColor = surface),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("${developer.title} · Lv. ${developer.level}", fontWeight = FontWeight.SemiBold)
                    Text("${developer.currentXp} / ${developer.nextLevelXp} XP", color = muted, fontSize = 12.sp)
                }
                Text("${developer.availableSkillPoints} SP", color = accent, fontWeight = FontWeight.Bold)
            }
            LinearProgressIndicator(
                progress = { (developer.currentXp.toFloat() / developer.nextLevelXp.coerceAtLeast(1)).coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxWidth().height(7.dp),
                color = accent,
                trackColor = elevated,
                gapSize = 0.dp,
                drawStopIndicator = {}
            )
        }
    }
}

@Composable
private fun FilterPill(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        color = if (selected) accent else surface,
        shape = RoundedCornerShape(10.dp),
        border = if (selected) null else BorderStroke(1.dp, border)
    ) {
        Text(
            label,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 9.dp),
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) background else muted,
            maxLines = 1
        )
    }
}