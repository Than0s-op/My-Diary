package dev.than0s.mydiary.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevation(
    val none: Dp = 0.dp,
    val extraSmall: Dp = 1.dp,
    val small: Dp = 2.dp,
    val medium: Dp = 4.dp,
    val large: Dp = 8.dp,
    val extraLarge: Dp = 12.dp,
    val extreme: Dp = 16.dp
)

val LocalElevation = compositionLocalOf { Elevation() }

val MaterialTheme.elevation: Elevation
    @Composable
    @ReadOnlyComposable
    get() = LocalElevation.current