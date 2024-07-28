package dev.than0s.mydiary.screen.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.AppState
import dev.than0s.mydiary.model.service.AccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : MyDiaryViewModel() {
    fun loadUser(onDone: () -> Unit) {
        if (!accountService.hasUser) {
            launchCatching(AppState.showSnackbar(viewModelScope)) {
                accountService.createAnonymousAccount()
                onDone()
            }
        } else onDone()
    }
}