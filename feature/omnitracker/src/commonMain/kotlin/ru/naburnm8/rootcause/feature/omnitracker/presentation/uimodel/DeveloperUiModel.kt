package ru.naburnm8.rootcause.feature.omnitracker.presentation.uimodel

data class DeveloperUiModel(
    val title: String,
    val level: Int,
    val currentXp: Int,
    val nextLevelXp: Int,
    val availableSkillPoints: Int
)
