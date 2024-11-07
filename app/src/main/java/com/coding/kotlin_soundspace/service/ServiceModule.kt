package com.coding.kotlin_soundspace.service

import com.coding.kotlin_soundspace.service.model.AccountService
import com.coding.kotlin_soundspace.service.model.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun bindAccountService(
        accountServiceImpl: AccountServiceImpl
    ): AccountService
}