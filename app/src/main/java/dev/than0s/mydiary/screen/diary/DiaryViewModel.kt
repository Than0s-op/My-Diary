package dev.than0s.mydiary.screen.diary

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.model.service.imple.AccountServiceImple
import dev.than0s.mydiary.screen.MyDiaryViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    accountService: AccountServiceImple,
    storageService: StorageService
) : MyDiaryViewModel() {
    val notes = storageService.notes
}