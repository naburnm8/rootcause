package ru.naburnm8.rootcause

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform