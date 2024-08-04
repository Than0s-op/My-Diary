package dev.than0s.mydiary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ScaffoldState {
    var floatingActionButtonState by mutableStateOf(ButtonActions())
    var bottomBarState by mutableStateOf(BottomBar(false))
}

data class ButtonActions(
    val onClick: (() -> Unit)? = null,
    val content: (@Composable () -> Unit)? = null,
    val visibility: Boolean = false
)

data class BottomBar(
    val visibility: Boolean = true
)
