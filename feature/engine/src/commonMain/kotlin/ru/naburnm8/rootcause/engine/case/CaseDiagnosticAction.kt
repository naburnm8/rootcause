package ru.naburnm8.rootcause.engine.case

data class CaseDiagnosticAction(
    val id: String,
    val toolId: String,
    val title: String,
    val costMinutes: Int,
    val result: String,
    val reveals: Set<String> = emptySet(),
    val repeatable: Boolean = false
)
