package dev.than0s.mydiary

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ScaffoldState {
    var floatingActionButtonState by mutableStateOf(ButtonActions())
    var bottomBarState by mutableStateOf(BottomBar(visibility = false))
    var topBarState by mutableStateOf(AppBar())
    var snackBarMessage by mutableStateOf("")
        private set

    fun showSnackBar(message: String) {
        snackBarMessage = message
    }
}

data class ButtonActions(
    val onClick: () -> Unit = {},
    val content: @Composable () -> Unit = {},
    val visibility: Boolean = false
)

data class BottomBar(
    val selected: Int = 0,
    val visibility: Boolean = true
)

data class AppBar(
    val title: String = "",
    val content: @Composable (RowScope.() -> Unit) = {},
)