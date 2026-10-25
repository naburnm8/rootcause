package ru.naburnm8.rootcause.engine.runner

sealed interface RunnerNode {
    data class Title(val value: String) : RunnerNode
    data class Body(val value: String) : RunnerNode
    data class Info(val label: String, val value: String) : RunnerNode
    data class Action(val label: String, val eventId: String) : RunnerNode
}
