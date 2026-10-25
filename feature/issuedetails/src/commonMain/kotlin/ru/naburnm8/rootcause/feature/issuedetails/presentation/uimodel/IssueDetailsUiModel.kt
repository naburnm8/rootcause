package ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel

data class IssueDetailsUiModel(
    val issue: IssueUiModel,
    val softDeadlineMinutes: Int,
    val stepsToReproduce: List<String>,
    val expectedResult: String,
    val actualResult: String,
    val reporter: String,
    val assignee: String,
    val comments: List<IssueCommentUiModel>
)
