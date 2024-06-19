package dev.than0s.mydiary.screen.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.AccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(val accountService: AccountService) :
    MyDiaryViewModel()