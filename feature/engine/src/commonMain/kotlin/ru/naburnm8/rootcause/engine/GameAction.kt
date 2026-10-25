package ru.naburnm8.rootcause.engine

sealed interface GameAction {
    data class RunnerEvent(val eventId: String) : GameAction
    data class Diagnose(val actionId: String) : GameAction
    data class CombineClues(val clueIds: Set<String>) : GameAction
    data class VerifyHypothesis(val hypothesisId: String, val optionId: String) : GameAction
    data class ImplementFix(val fixId: String) : GameAction
    data object TestFix : GameAction
    data object SubmitFix : GameAction
}