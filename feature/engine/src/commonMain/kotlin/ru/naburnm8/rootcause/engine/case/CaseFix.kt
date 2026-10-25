package ru.naburnm8.rootcause.engine.case

data class CaseFix(
    val id: String,
    val title: String,
    val implementationMinutes: Int,
    val requiredSkillsIds: List<String> = listOf()
)
