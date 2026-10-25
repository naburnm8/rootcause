package ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel

import ru.naburnm8.rootcause.feature.issuedetails.model.IssuePriority
import ru.naburnm8.rootcause.feature.issuedetails.model.IssueStatus

data class IssueUiModel(
    val id: String,
    val title: String,
    val description: String,
    val status: IssueStatus,
    val priority: IssuePriority,
    val spentMinutes: Int = 0,
    val hardDeadlineMinutes: Int = 0,
    val bestRating: String? = null
)