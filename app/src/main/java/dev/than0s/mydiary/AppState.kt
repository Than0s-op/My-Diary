package dev.than0s.mydiary

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AppState {
    lateinit var snackbarHostState: SnackbarHostState
    fun showSnackbar(scope: CoroutineScope): (String) -> Unit {
        return { message: String ->
            scope.launch {
                snackbarHostState.showSnackbar(message)
            }
            Unit
        }
    }
}