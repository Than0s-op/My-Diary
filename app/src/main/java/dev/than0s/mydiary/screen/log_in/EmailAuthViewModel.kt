package dev.than0s.mydiary.screen.log_in

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.EmailAccountService
import dev.than0s.mydiary.screen.MyDiaryViewModel
import javax.inject.Inject

@HiltViewModel
class EmailAuthViewModel @Inject constructor(
    private val emailAccountService: EmailAccountService
) : MyDiaryViewModel()