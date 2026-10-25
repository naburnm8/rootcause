package ru.naburnm8.rootcause.feature.omnitracker.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.naburnm8.rootcause.core.ds.error
import ru.naburnm8.rootcause.core.ds.muted
import ru.naburnm8.rootcause.core.ds.warning
import ru.naburnm8.rootcause.feature.omnitracker.model.IssuePriority


val IssuePriority.title: String
    @Composable get() = when (this) {
        IssuePriority.MEDIUM -> "Medium"
        IssuePriority.HIGH -> "High"
        IssuePriority.CRITICAL -> "Critical"
    }

val IssuePriority.color: Color
    get() = when (this) {
        IssuePriority.MEDIUM -> muted
        IssuePriority.HIGH -> warning
        IssuePriority.CRITICAL -> error
    }