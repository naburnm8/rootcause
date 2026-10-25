package ru.naburnm8.rootcause.feature.omnitracker.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naburnm8.rootcause.core.ds.accent
import ru.naburnm8.rootcause.core.ds.border
import ru.naburnm8.rootcause.core.ds.elevated
import ru.naburnm8.rootcause.core.ds.error
import ru.naburnm8.rootcause.core.ds.muted
import ru.naburnm8.rootcause.core.ds.success
import ru.naburnm8.rootcause.core.ds.surface
import ru.naburnm8.rootcause.core.ds.warning
import ru.naburnm8.rootcause.feature.omnitracker.model.IssuePriority
import ru.naburnm8.rootcause.feature.omnitracker.model.IssueStatus
import ru.naburnm8.rootcause.feature.omnitracker.presentation.uimodel.IssueUiModel

@Composable
internal fun IssueCard(
    issue: IssueUiModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, border),
        colors = CardDefaults.cardColors(containerColor = surface)
    ) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("${issue.id}  ·  BUG", color = muted, fontSize = 11.sp, modifier = Modifier.weight(1f))
                StatusBadge(issue.status)
            }
            Text(issue.title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(issue.description, color = muted, fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically) {
                val priorityColor = issue.priority.color
                Text("● ${issue.priority.title} priority", color = priorityColor, fontSize = 12.sp, modifier = Modifier.weight(1f))
                if (issue.status == IssueStatus.IN_PROGRESS && issue.hardDeadlineMinutes > 0) {
                    Text("${issue.spentMinutes} / ${issue.hardDeadlineMinutes} min", color = muted, fontSize = 12.sp)
                } else if (issue.bestRating != null) {
                    Text("Best: ${issue.bestRating}", color = success, fontSize = 12.sp)
                } else {
                    Text("›", color = muted, fontSize = 18.sp)
                }
            }
            if (issue.status == IssueStatus.IN_PROGRESS && issue.hardDeadlineMinutes > 0) {
                LinearProgressIndicator(
                    progress = { (issue.spentMinutes.toFloat() / issue.hardDeadlineMinutes).coerceIn(0f, 1f) },
                    modifier = Modifier.fillMaxWidth().height(5.dp),
                    color = accent,
                    trackColor = elevated,
                    gapSize = 0.dp,
                    drawStopIndicator = {}
                )
            }
        }
    }
}

@Composable
private fun StatusBadge(
    status: IssueStatus
) {
    val color = status.containerColor
    Surface(color = color.copy(alpha = .14f), shape = RoundedCornerShape(8.dp)) {
        Text(status.title, color = color, fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp))
    }
}
