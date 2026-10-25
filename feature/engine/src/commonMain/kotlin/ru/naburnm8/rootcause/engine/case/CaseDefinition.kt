package ru.naburnm8.rootcause.engine.case

import ru.naburnm8.rootcause.engine.case.hypothesis.Hypothesis
import ru.naburnm8.rootcause.engine.runner.RunnerScreen
import ru.naburnm8.rootcause.engine.runner.RunnerTransition

data class CaseDefinition(
    val schemaVersion: Int = 1,
    val id: String,
    val title: String,
    val softDeadline: Int,
    val hardDeadline: Int,
    val initialScreenId: String,
    val screens: List<RunnerScreen>,
    val transitions: List<RunnerTransition>,
    val diagnostics: List<CaseDiagnosticAction>,
    val clues: List<CaseClue>,
    val hypotheses: List<Hypothesis>,
    val fixes: List<CaseFix> = emptyList(),
    val conclusions: List<CaseConclusion> = emptyList()
)
