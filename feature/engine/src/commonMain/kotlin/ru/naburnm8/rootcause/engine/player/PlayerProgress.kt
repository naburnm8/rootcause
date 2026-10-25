package ru.naburnm8.rootcause.engine.player

data class PlayerProgress(
    val level: Int = 1,
    val experience: Int = 0,
    val skillPoints: Int = 0,
    val skills: Map<String, Int> = emptyMap(),
    val unlockedToolIds: Set<String> = setOf("runner", "logcat", "network")
) {
    val analysisRank: Int get() = skills["analytical_thinking"] ?: 0
}
