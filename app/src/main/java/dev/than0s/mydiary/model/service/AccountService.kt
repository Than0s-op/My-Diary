package dev.than0s.mydiary.model.service

import dev.than0s.mydiary.screen.settings.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val isAnonymous: Boolean
    val currentUser: Flow<User>
    suspend fun createAnonymousAccount()
    suspend fun signOut()
    suspend fun deleteAccount()
}