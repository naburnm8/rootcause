package ru.naburnm8.rootcause.feature.issuedetails.presentation.uimodel

data class IssueCommentUiModel(
    val author: String,
    val role: String,
    val avatarSymbol: String,
    val body: String,
    val time: String
)
