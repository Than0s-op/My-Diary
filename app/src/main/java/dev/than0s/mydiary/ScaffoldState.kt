package dev.than0s.mydiary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ScaffoldState {
    object FloatingActionButton {
        var state by mutableStateOf(ButtonActions())
    }
}

data class ButtonActions(
    val onClick: (() -> Unit)? = null,
    val content: (@Composable () -> Unit)? = null,
    val visibility: Boolean = false
)