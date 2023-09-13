package com.example.creditcards.di

import android.app.Application
import com.example.creditcards.database.dao.CreditCardDatabase
import com.example.creditcards.database.source.LocalDataSource
import com.example.creditcards.database.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [CreditCardLocalModule.Binders::class])
@InstallIn(SingletonComponent::class)
class CreditCardLocalModule {
    @Module
    @InstallIn(SingletonComponent::class)
    interface Binders {
        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataSource
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = CreditCardDatabase.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesCardsDao(
        data: CreditCardDatabase
    ) = data.getCreditCardDao()
}