package dev.than0s.mydiary.screen.diary

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.model.service.imple.AccountServiceImple
import dev.than0s.mydiary.model.service.imple.StorageServiceImple
import dev.than0s.mydiary.screen.MyDiaryViewModel
import kotlinx.coroutines.launch

class DiaryViewModel(storageService:StorageService): MyDiaryViewModel() {
    val notes = storageService.notes
    init{
        viewModelScope.launch{
            val firebaseAuth = FirebaseAuth.getInstance()
            val accountService = AccountServiceImple(firebaseAuth)
            if(!accountService.hasUser) accountService.createAnonymousAccount()
        }
    }
}