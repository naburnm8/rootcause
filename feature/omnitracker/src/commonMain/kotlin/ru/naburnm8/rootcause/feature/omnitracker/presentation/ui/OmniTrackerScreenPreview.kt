package ru.naburnm8.rootcause.feature.omnitracker.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.naburnm8.rootcause.core.ds.RootCauseTheme
import ru.naburnm8.rootcause.feature.issuedetails.model.IssuePriority
import ru.naburnm8.rootcause.feature.issuedetails.model.IssueStatus
import ru.naburnm8.rootcause.feature.omnitracker.presentation.uimodel.DeveloperUiModel
import ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel.IssueUiModel

private val previewIssues = listOf(
    IssueUiModel(
        "OMNI-102",
        "Duplicate Order",
        "После тайм-аута пользователи иногда получают два заказа.",
        IssueStatus.IN_PROGRESS, IssuePriority.HIGH,
        18,
        45)
    ,
    IssueUiModel("OMNI-103",
        "Stale Order Status",
        "Неактуальный статус доставки в истории заказов.",
        IssueStatus.TO_DO,
        IssuePriority.MEDIUM
    ),
    IssueUiModel("OMNI-104",
        "Slow Product List",
        "При прокрутке каталога появляются рывки.",
        IssueStatus.TO_DO,
        IssuePriority.HIGH
    ),
    IssueUiModel(
        "OMNI-105",
        "Concurrent Cart Updates",
        "При быстрых нажатиях количество товара меняется неверно.",
        IssueStatus.TO_DO,
        IssuePriority.CRITICAL
    ),
    IssueUiModel("OMNI-101",
        "The Crash",
        "Аварийное завершение при открытии карточки товара.",
        IssueStatus.DONE,
        IssuePriority.MEDIUM,
        bestRating = "A"
    )
)

@Preview
@Composable
private fun OmniTrackerPreview() {
    RootCauseTheme {
        OmniTrackerScreen(
            issues = previewIssues,
            developer = DeveloperUiModel(
                "Junior Developer",
                2,
                140,
                200,
                1
            )
        )
    }
}