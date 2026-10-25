package ru.naburnm8.rootcause.engine.case

import ru.naburnm8.rootcause.engine.case.hypothesis.HypothesisVerificationRecord
import ru.naburnm8.rootcause.engine.case.log.CaseLogEntry
import ru.naburnm8.rootcause.engine.case.log.CaseLogKind

data class CaseState(
    val screenId: String,
    val elapsedMinutes: Int = 0,
    val status: CaseStatus = CaseStatus.ACTIVE,
    val revealedClueIds: Set<String> = emptySet(),
    val discoveredHypothesisIds: Set<String> = emptySet(),
    val verificationRecords: Map<String, HypothesisVerificationRecord> = emptyMap(),
    val performedDiagnosticIds: Set<String> = emptySet(),
    val log: List<CaseLogEntry> = listOf(CaseLogEntry(0, CaseLogKind.SYSTEM, "Тикет принят в работу")),
    val lastResult: String = "",
    val activeFixId: String? = null
)
