package dev.than0s.mydiary.model.service

interface EmailAccountService : AccountService {
    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun linkAccount(email: String, password: String)
}