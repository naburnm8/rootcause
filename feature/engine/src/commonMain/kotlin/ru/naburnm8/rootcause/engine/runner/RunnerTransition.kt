package ru.naburnm8.rootcause.engine.runner

data class RunnerTransition(
    val screenId: String,
    val eventId: String,
    val nextScreenId: String,
    val minutes: Int = 0,
    val reveals: Set<String> = emptySet(),
    val message: String
)
