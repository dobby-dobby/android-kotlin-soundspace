package com.coding.kotlin_soundspace.service

import com.coding.kotlin_soundspace.screen.loginFeature.LoginViewModel
import com.coding.kotlin_soundspace.service.model.AccountService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LoginViewModelModule {
    @Provides
    fun provideLoginViewModel(accountService: AccountService): LoginViewModel {
        return LoginViewModel(accountService)
    }
}