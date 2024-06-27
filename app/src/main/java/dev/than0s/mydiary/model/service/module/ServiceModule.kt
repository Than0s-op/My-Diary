package dev.than0s.mydiary.model.service.module

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.than0s.mydiary.model.service.AccountService
import dev.than0s.mydiary.model.service.EmailAccountService
import dev.than0s.mydiary.model.service.GoogleAccountService
import dev.than0s.mydiary.model.service.StorageService
import dev.than0s.mydiary.model.service.imple.AccountServiceImple
import dev.than0s.mydiary.model.service.imple.EmailAccountServiceImple
import dev.than0s.mydiary.model.service.imple.GoogleAccountServiceImple
import dev.than0s.mydiary.model.service.imple.StorageServiceImple

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImple): AccountService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImple): StorageService

    @Binds
    abstract fun provideGoogleAccountService(impl: GoogleAccountServiceImple): GoogleAccountService

    @Binds
    abstract fun provideEmailAccountService(impl: EmailAccountServiceImple): EmailAccountService
}

@Module
@InstallIn(SingletonComponent::class)
object signInClient {
    @Provides
    fun provideSignInClient(@ApplicationContext appContext: Context): SignInClient =
        Identity.getSignInClient(appContext)

}