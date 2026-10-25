package ru.naburnm8.rootcause.feature.issuedetails.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.naburnm8.rootcause.core.ds.RootCauseTheme
import ru.naburnm8.rootcause.feature.issuedetails.model.IssuePriority
import ru.naburnm8.rootcause.feature.issuedetails.model.IssueStatus
import ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel.IssueCommentUiModel
import ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel.IssueDetailsUiModel
import ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel.IssueUiModel

private val duplicateOrderPreview = IssueDetailsUiModel(
    issue = IssueUiModel(
        id = "OMNI-102", title = "Duplicate Order",
        description = "Пользователи сообщают, что после оформления заказа и кратковременного зависания приложения иногда появляются два одинаковых заказа.",
        status = IssueStatus.IN_PROGRESS, priority = IssuePriority.HIGH,
        spentMinutes = 18, hardDeadlineMinutes = 45
    ),
    softDeadlineMinutes = 30,
    stepsToReproduce = listOf(
        "Добавить товар в корзину.",
        "Перейти к оформлению заказа.",
        "Нажать «Оформить» при нестабильном соединении.",
        "Проверить историю заказов."
    ),
    expectedResult = "Один заказ после одного оформления.",
    actualResult = "Иногда создаются два заказа.",
    reporter = "Лита · QA Engineer",
    assignee = "You · Mobile Developer",
    comments = listOf(
        IssueCommentUiModel(
            "Лита",
            "QA",
            "🐈",
            "Воспроизводится не всегда. Иногда после зависания появляется сразу два заказа.",
            "10:42"
        ),
        IssueCommentUiModel(
            "Марк",
            "Team Lead",
            "♫",
            "Нужно разобраться, почему повторное оформление иногда приводит к созданию второго заказа.",
            "10:58"
        )
    )
)

@Preview
@Composable
private fun IssueDetailsPreview() {
    RootCauseTheme {
        IssueDetailsScreen(details = duplicateOrderPreview)
    }
}