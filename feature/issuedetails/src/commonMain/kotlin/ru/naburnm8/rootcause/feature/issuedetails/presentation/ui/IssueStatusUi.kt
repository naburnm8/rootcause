package ru.naburnm8.rootcause.feature.issuedetails.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.naburnm8.rootcause.core.ds.accent
import ru.naburnm8.rootcause.core.ds.muted
import ru.naburnm8.rootcause.core.ds.success
import ru.naburnm8.rootcause.feature.issuedetails.model.IssueStatus

val IssueStatus.title: String
    @Composable get() = when (this) {
        IssueStatus.IN_PROGRESS -> "In progress"
        IssueStatus.DONE -> "Done"
        IssueStatus.TO_DO -> "To do"
    }

val IssueStatus.containerColor: Color
    get() = when (this) {
        IssueStatus.IN_PROGRESS -> accent
        IssueStatus.TO_DO -> muted
        IssueStatus.DONE -> success
    }