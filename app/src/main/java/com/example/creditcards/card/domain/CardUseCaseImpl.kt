package com.example.creditcards.card.domain

import com.example.creditcards.card.data.repository.CardRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CardUseCaseImpl @Inject constructor(private val cardRepository: CardRepository) :
    CardUseCase {
    override suspend fun getCards(size: Int) = cardRepository.getCards(size)
}
