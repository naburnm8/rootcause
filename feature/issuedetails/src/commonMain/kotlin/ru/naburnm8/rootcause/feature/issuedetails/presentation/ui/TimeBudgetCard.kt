package ru.naburnm8.rootcause.feature.issuedetails.presentation.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naburnm8.rootcause.core.ds.accent
import ru.naburnm8.rootcause.core.ds.border
import ru.naburnm8.rootcause.core.ds.elevated
import ru.naburnm8.rootcause.core.ds.muted
import ru.naburnm8.rootcause.core.ds.surface
import ru.naburnm8.rootcause.core.ds.text
import ru.naburnm8.rootcause.core.ds.warning

@Composable
internal fun TimeBudgetCard(spentMinutes: Int, softDeadlineMinutes: Int, hardDeadlineMinutes: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = surface),
        border = BorderStroke(1.dp, border)
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Игровое время", color = muted, fontSize = 12.sp)
                Text("$spentMinutes / $hardDeadlineMinutes мин", color = text, fontWeight = FontWeight.SemiBold)
            }
            LinearProgressIndicator(
                progress = { (spentMinutes.toFloat() / hardDeadlineMinutes.coerceAtLeast(1)).coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxWidth().height(7.dp),
                color = if (spentMinutes > softDeadlineMinutes) warning else accent,
                trackColor = elevated,
                gapSize = 0.dp,
                drawStopIndicator = {}
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Soft: $softDeadlineMinutes мин", color = muted, fontSize = 12.sp)
                Text("Hard: $hardDeadlineMinutes мин", color = muted, fontSize = 12.sp)
            }
        }
    }
}