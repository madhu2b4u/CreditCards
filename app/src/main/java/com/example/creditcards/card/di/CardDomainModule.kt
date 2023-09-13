package com.example.creditcards.card.di

import com.example.creditcards.card.data.repository.CardRepository
import com.example.creditcards.card.data.repository.CardRepositoryImpl
import com.example.creditcards.card.domain.CardUseCase
import com.example.creditcards.card.domain.CardUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CardDomainModule {

    @Binds
    internal abstract fun bindsRepository(
        repoImpl: CardRepositoryImpl
    ): CardRepository


    @Binds
    internal abstract fun bindsUseCase(
        mCardUseCase: CardUseCaseImpl
    ): CardUseCase


}