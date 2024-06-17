package dev.than0s.mydiary.model.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.than0s.mydiary.model.service.AccountService
import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.model.service.imple.AccountServiceImple
import dev.than0s.mydiary.model.service.imple.StorageServiceImple

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImple): AccountService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImple): StorageService
}