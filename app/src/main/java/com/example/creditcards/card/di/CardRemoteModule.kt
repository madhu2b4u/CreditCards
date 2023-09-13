package com.example.creditcards.card.di


import com.example.creditcards.card.data.remote.services.CardService
import com.example.creditcards.card.data.remote.source.CardRemoteDataSource
import com.example.creditcards.card.data.remote.source.CardRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module(includes = [CardRemoteModule.Binders::class])
@InstallIn(SingletonComponent::class)
class CardRemoteModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binders {
        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: CardRemoteDataSourceImpl
        ): CardRemoteDataSource
    }

    @Provides
    fun providesCardService(retrofit: Retrofit): CardService =
        retrofit.create(CardService::class.java)

}