package ru.naburnm8.rootcause.engine.case.hypothesis

data class HypothesisEvidenceRequirement(
    val clueIds: Set<String>,
    val minAnalysisRank: Int = 0
)