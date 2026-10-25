package ru.naburnm8.rootcause.feature.issuedetails.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.naburnm8.rootcause.core.ds.accent
import ru.naburnm8.rootcause.core.ds.elevated
import ru.naburnm8.rootcause.core.ds.muted
import ru.naburnm8.rootcause.core.ds.text
import ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel.IssueCommentUiModel

@Composable
internal fun IssueCommentItem(
    modifier: Modifier = Modifier,
    comment: IssueCommentUiModel
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            Modifier.size(38.dp).background(elevated, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(comment.avatarSymbol, color = accent, fontSize = 20.sp)
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                Text(comment.author, color = text, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Text(comment.role + " · " + comment.time, color = muted, fontSize = 11.sp)
            }
            Text(comment.body, color = text, fontSize = 13.sp, lineHeight = 19.sp)
        }
    }
}