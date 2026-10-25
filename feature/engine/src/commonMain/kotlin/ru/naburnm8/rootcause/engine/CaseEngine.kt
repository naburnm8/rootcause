package ru.naburnm8.rootcause.engine

import ru.naburnm8.rootcause.engine.case.CaseDefinition
import ru.naburnm8.rootcause.engine.case.CaseState
import ru.naburnm8.rootcause.engine.case.CaseStatus
import ru.naburnm8.rootcause.engine.case.hypothesis.HypothesisVerificationRecord
import ru.naburnm8.rootcause.engine.case.log.CaseLogEntry
import ru.naburnm8.rootcause.engine.case.log.CaseLogKind
import ru.naburnm8.rootcause.engine.player.PlayerProgress
import ru.naburnm8.rootcause.engine.runner.RunnerScreen

class CaseEngine(val definition: CaseDefinition) {
    init {
        require(definition.schemaVersion == 1) { "Unsupported case schema ${definition.schemaVersion}" }
        require(definition.softDeadline in 0..definition.hardDeadline)
        require(definition.screens.any { it.id == definition.initialScreenId })
    }

    fun initialState(): CaseState = CaseState(screenId = definition.initialScreenId)

    fun screen(state: CaseState): RunnerScreen =
        definition.screens.first { it.id == state.screenId }

    fun reduce(state: CaseState, progress: PlayerProgress, action: GameAction): CaseState {
        if (state.status != CaseStatus.ACTIVE) return state.copy(lastResult = "Тикет уже завершён")
        return when (action) {
            is GameAction.RunnerEvent -> runnerEvent(state, action.eventId)
            is GameAction.Diagnose -> diagnose(state, progress, action.actionId)
            is GameAction.CombineClues -> combine(state, progress, action.clueIds)
            is GameAction.VerifyHypothesis -> verify(state, progress, action.hypothesisId, action.optionId)
            is GameAction.ImplementFix -> implementFix(state, progress, action.fixId)
            GameAction.TestFix -> testFix(state, progress)
            GameAction.SubmitFix -> submitFix(state, progress)
        }
    }

    private fun runnerEvent(state: CaseState, eventId: String): CaseState {
        val t = definition.transitions.firstOrNull { it.screenId == state.screenId && it.eventId == eventId }
            ?: return state.copy(lastResult = "Действие недоступно")
        if (!canSpend(state, t.minutes)) return noTime(state)
        return advance(state, t.minutes, t.message, CaseLogKind.RUNNER, t.reveals)
            .copy(screenId = t.nextScreenId)
    }

    private fun diagnose(
        state: CaseState,
        progress: PlayerProgress,
        id: String
    ): CaseState {
        val d = definition.diagnostics.firstOrNull { it.id == id }
            ?: return state.copy(lastResult = "Неизвестное действие")
        if (d.toolId !in progress.unlockedToolIds) return state.copy(lastResult = "Инструмент не открыт")
        if (!d.repeatable && id in state.performedDiagnosticIds) return state.copy(lastResult = d.result)
        val cost = adjustedCost(d.costMinutes, progress, d.toolId)
        if (!canSpend(state, cost)) return noTime(state)
        return advance(state, cost, d.result, CaseLogKind.DIAGNOSTIC, d.reveals)
            .copy(performedDiagnosticIds = state.performedDiagnosticIds + id)
    }

    private fun combine(
        state: CaseState,
        progress: PlayerProgress,
        selected: Set<String>
    ): CaseState {
        if (selected.size < 2 || !state.revealedClueIds.containsAll(selected))
            return state.copy(lastResult = "Нужно выбрать минимум две найденные улики")
        val matched = definition.hypotheses.filter { h ->
            h.evidenceRequirements.any { it.clueIds == selected && progress.analysisRank >= it.minAnalysisRank }
        }
        val newlyFound = matched.filterNot { it.id in state.discoveredHypothesisIds }
        if (newlyFound.isEmpty()) return state.copy(lastResult = if (matched.isEmpty())
            "Эта комбинация не даёт новой гипотезы" else "Гипотеза уже открыта")
        val cost = 1 // Future: resolve from balance configuration and Analysis skills.
        if (!canSpend(state, cost)) return noTime(state)
        return advance(state, cost, "Открыта гипотеза: ${newlyFound.joinToString { it.title }}", CaseLogKind.HYPOTHESIS)
            .copy(discoveredHypothesisIds = state.discoveredHypothesisIds + newlyFound.map { it.id })
    }

    private fun verify(state: CaseState, progress: PlayerProgress, hypothesisId: String, optionId: String): CaseState {
        val hypothesis = definition.hypotheses.firstOrNull { it.id == hypothesisId }
            ?: return state.copy(lastResult = "Неизвестная гипотеза")
        if (hypothesisId !in state.discoveredHypothesisIds) return state.copy(lastResult = "Гипотеза ещё не открыта")
        if (hypothesisId in state.verificationRecords) return state.copy(lastResult = "Гипотеза уже проверена")
        val option = hypothesis.verificationOptions.firstOrNull { it.id == optionId }
            ?: return state.copy(lastResult = "Способ проверки не найден")
        if (option.toolId !in progress.unlockedToolIds) return state.copy(lastResult = "Инструмент не открыт")
        val cost = adjustedCost(option.minutes, progress, option.toolId)
        if (!canSpend(state, cost)) return noTime(state)
        return advance(state, cost, option.result, CaseLogKind.VERIFICATION, option.reveals).copy(
            verificationRecords = state.verificationRecords + (hypothesisId to HypothesisVerificationRecord(
                option.id,
                option.outcome,
                option.result
            ))
        )
    }

    private fun adjustedCost(
        base: Int,
        progress: PlayerProgress,
        toolId: String
    ): Int {
        // Extension point: tool-specific upgrade ranks, stacked multipliers and cost cap.
        // The prototype intentionally charges authored base cost until balancing is implemented.
        require(base >= 0)
        return base
    }

    private fun implementFix(
        state: CaseState,
        progress: PlayerProgress,
        fixId: String
    ): CaseState {
        // Future work: skill requirements, implementation time, rollback, fix side effects.
        TODO("Implement fix application and rollback before enabling the Fixes panel")
    }

    private fun testFix(
        state: CaseState,
        progress: PlayerProgress
    ): CaseState {
        // Future work: simulated post-fix App Runner outcome and regression tests.
        TODO("Implement local fix testing before enabling the Fixes panel")
    }

    private fun submitFix(
        state: CaseState,
        progress: PlayerProgress
    ): CaseState {
        // Future work: conclusions, grading, XP and persistence of best run.
        TODO("Implement submission and rewards before enabling the Fixes panel")
    }

    private fun canSpend(state: CaseState, cost: Int) =
        cost >= 0 && state.elapsedMinutes + cost <= definition.hardDeadline

    private fun noTime(state: CaseState) = state.copy(lastResult = "Недостаточно игровых минут")

    private fun advance(
        state: CaseState,
        minutes: Int,
        message: String,
        kind: CaseLogKind,
        clues: Set<String> = emptySet()
    ): CaseState {
        val elapsed = state.elapsedMinutes + minutes
        return state.copy(
            elapsedMinutes = elapsed,
            status = if (elapsed >= definition.hardDeadline) CaseStatus.EXPIRED else CaseStatus.ACTIVE,
            revealedClueIds = state.revealedClueIds + clues,
            log = state.log + CaseLogEntry(elapsed, kind, message),
            lastResult = message
        )
    }
}