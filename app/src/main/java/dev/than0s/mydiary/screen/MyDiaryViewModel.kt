package dev.than0s.mydiary.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class MyDiaryViewModel : ViewModel() {
    fun launchCatching(
        errorMassage: (message: String) -> Unit,
        block: suspend CoroutineScope.() -> Unit
    ) =
        viewModelScope.launch(
            context = CoroutineExceptionHandler { _, throwable ->
                throwable.message?.let {
                    errorMassage(it)
                }
            },
            block = block
        )
}