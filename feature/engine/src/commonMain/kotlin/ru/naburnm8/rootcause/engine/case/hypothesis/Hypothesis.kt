package ru.naburnm8.rootcause.engine.case.hypothesis

data class Hypothesis(
    val id: String,
    val title: String,
    val description: String,
    val evidenceRequirements: List<HypothesisEvidenceRequirement>,
    val verificationOptions: List<HypothesisVerificationOption>
)
