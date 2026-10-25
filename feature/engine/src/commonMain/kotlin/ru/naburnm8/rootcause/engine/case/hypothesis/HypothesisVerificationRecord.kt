package ru.naburnm8.rootcause.engine.case.hypothesis

data class HypothesisVerificationRecord(
    val optionId: String,
    val outcome: HypothesisVerificationOutcome,
    val result: String
)
