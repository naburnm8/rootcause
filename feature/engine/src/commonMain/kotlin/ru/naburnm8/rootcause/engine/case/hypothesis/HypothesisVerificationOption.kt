package ru.naburnm8.rootcause.engine.case.hypothesis

data class HypothesisVerificationOption(
    val id: String,
    val toolId: String,
    val minutes: Int,
    val outcome: HypothesisVerificationOutcome,
    val result: String,
    val reveals: Set<String> = emptySet()
)
