package ru.naburnm8.rootcause.feature.issuedetails.presentation.ui

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naburnm8.rootcause.core.ds.accent
import ru.naburnm8.rootcause.core.ds.background
import ru.naburnm8.rootcause.core.ds.border
import ru.naburnm8.rootcause.core.ds.error
import ru.naburnm8.rootcause.core.ds.muted
import ru.naburnm8.rootcause.core.ds.surface
import ru.naburnm8.rootcause.core.ds.text
import ru.naburnm8.rootcause.core.ds.warning
import ru.naburnm8.rootcause.feature.issuedetails.model.IssuePriority
import ru.naburnm8.rootcause.feature.issuedetails.model.IssueStatus
import ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel.IssueDetailsUiModel

@Composable
fun IssueDetailsScreen(
    details: IssueDetailsUiModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onPrimaryAction: () -> Unit = {}
) {
    val issue = details.issue
    Scaffold(
        modifier = modifier,
        containerColor = background,
        topBar = {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextButton(onClick = onBack, contentPadding = PaddingValues(horizontal = 4.dp)) {
                    Text("‹", color = accent, fontSize = 30.sp)
                }
                Column(Modifier.weight(1f)) {
                    Text(issue.id, color = text, fontWeight = FontWeight.SemiBold)
                    Text("Omni Tech · Mobile Team", fontSize = 12.sp, color = muted)
                }
                Text("BUG", color = muted, fontSize = 11.sp)
            }
        },
        bottomBar = {
            Surface(color = background, shadowElevation = 10.dp) {
                Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
                    Button(
                        onClick = onPrimaryAction,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = accent,
                            contentColor = background
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        val action = when (issue.status) {
                            IssueStatus.TO_DO -> "Начать расследование"
                            IssueStatus.IN_PROGRESS -> "Продолжить расследование"
                            IssueStatus.DONE -> "Просмотреть результат"
                        }
                        Text(action, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(issue.title, fontSize = 27.sp, fontWeight = FontWeight.Bold, color = text)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                StatusBadge(issue.status)
                InfoPill(
                    label = issue.priority.title + " priority",
                    tint = if (issue.priority == IssuePriority.CRITICAL) error else warning
                )
                InfoPill(label = "Bug", tint = muted)
            }
            TimeBudgetCard(
                spentMinutes = issue.spentMinutes,
                softDeadlineMinutes = details.softDeadlineMinutes,
                hardDeadlineMinutes = issue.hardDeadlineMinutes
            )
            SectionTitle(label = "Описание")
            Text(issue.description, color = text, fontSize = 14.sp, lineHeight = 21.sp)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MetadataItem(
                    Modifier.weight(1f),
                    "Reporter",
                    details.reporter
                )
                MetadataItem(
                    Modifier.weight(1f),
                    "Assignee",
                    details.assignee
                    )
            }
            HorizontalDivider(color = border)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionTitle(label = "Шаги")
                details.stepsToReproduce.forEachIndexed { index, step ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("${index + 1}.", color = accent, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(step, color = text, fontSize = 14.sp, lineHeight = 20.sp)
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ResultCard(
                    Modifier.weight(1f),
                    "Expected",
                    details.expectedResult
                    )
                ResultCard(Modifier.weight(1f),
                    "Actual",
                    details.actualResult,
                    )
            }
            HorizontalDivider(color = border)
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.weight(1f)) { SectionTitle(label = "Коментарии") }
                    Text("${details.comments.size} коментария", color = muted, fontSize = 12.sp)
                }
                details.comments.forEach {
                    comment -> IssueCommentItem(
                        comment = comment
                    )
                }
            }
            Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
private fun InfoPill(
    modifier: Modifier = Modifier,
    label: String,
    tint: Color
) {
    Surface(
        modifier = modifier,
        color = tint.copy(alpha = .13f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(label, color = tint, fontSize = 11.sp, modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp))
    }
}

@Composable
private fun SectionTitle(
    modifier: Modifier = Modifier,
    label: String
) {
    Text(
        modifier = modifier,
        text = label,
        fontWeight = FontWeight.Bold,
        color = text,
        fontSize = 16.sp
    )
}

@Composable
private fun ResultCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Column(
        modifier.background(surface, RoundedCornerShape(12.dp)).padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(label, color = muted, fontSize = 12.sp)
        Text(value, color = text, fontSize = 13.sp, lineHeight = 18.sp)
    }
}

@Composable
private fun MetadataItem(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, color = muted, fontSize = 12.sp)
        Text(value, color = text, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}
