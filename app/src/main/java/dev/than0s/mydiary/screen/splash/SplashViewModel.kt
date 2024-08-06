package dev.than0s.mydiary.screen.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.ScaffoldState
import dev.than0s.mydiary.model.service.AccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : MyDiaryViewModel() {
    fun loadUser(onDone: () -> Unit) {
        if (!accountService.hasUser) {
            launchCatching(ScaffoldState::showSnackBar) {
                accountService.createAnonymousAccount()
                onDone()
            }
        } else onDone()
    }
}