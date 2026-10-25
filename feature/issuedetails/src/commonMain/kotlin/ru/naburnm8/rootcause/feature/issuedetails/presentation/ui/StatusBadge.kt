package ru.naburnm8.rootcause.feature.issuedetails.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naburnm8.rootcause.feature.issuedetails.model.IssueStatus

@Composable
internal fun StatusBadge(
    status: IssueStatus
) {
    val color = status.containerColor
    Surface(color = color.copy(alpha = .14f), shape = RoundedCornerShape(8.dp)) {
        Text(status.title, color = color, fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp))
    }
}