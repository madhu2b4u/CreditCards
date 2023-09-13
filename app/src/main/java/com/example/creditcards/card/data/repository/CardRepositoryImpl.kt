package com.example.creditcards.card.data.repository

import androidx.lifecycle.liveData
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.card.data.remote.source.CardRemoteDataSource
import com.example.creditcards.common.Result
import com.example.creditcards.database.source.LocalDataSource
import javax.inject.Inject

internal class CardRepositoryImpl @Inject constructor(
    private val remoteDataSource: CardRemoteDataSource,
    private val localDataSource: LocalDataSource
) : CardRepository {
    override suspend fun getCards(size: Int) = liveData {
        emit(Result.loading())
        try {
            var cards: List<CardDO>? = localDataSource.getCards()
            if (cards == null) {
                cards = remoteDataSource.getCards(size)
                localDataSource.saveCards(cards)
            }
            emit(Result.success(cards))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: "", null))
        }
    }
}