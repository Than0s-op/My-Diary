package dev.than0s.mydiary.model.service

import android.content.Intent

interface GoogleAccountService : AccountService {
    suspend fun authenticate(data: Intent)
    suspend fun linkAccount(data: Intent)
}