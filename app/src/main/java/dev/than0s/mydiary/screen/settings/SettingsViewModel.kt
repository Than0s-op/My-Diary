package dev.than0s.mydiary.screen.settings

import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.than0s.mydiary.model.service.AccountService
import dev.than0s.mydiary.model.service.imple.GoogleAccountServiceImple
import dev.than0s.mydiary.screen.MyDiaryViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val accountService: GoogleAccountServiceImple
) : MyDiaryViewModel(){

}