package ru.naburnm8.rootcause.engine.case.log

data class CaseLogEntry(
    val minute: Int,
    val kind: CaseLogKind,
    val message: String
)
