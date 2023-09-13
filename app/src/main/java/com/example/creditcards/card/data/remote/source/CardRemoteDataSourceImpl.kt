package com.example.creditcards.card.data.remote.source

import com.example.creditcards.card.data.remote.services.CardService
import com.example.creditcards.di.qualifiers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CardRemoteDataSourceImpl @Inject constructor(
    private val service: CardService,
    @IO private val context: CoroutineContext
) : CardRemoteDataSource {
    override suspend fun getCards(size: Int) = withContext(context) {
        val response = service.getCardAsync(size).await()
        if (response.isSuccessful)
            response.body() ?: throw Exception(response.message())
        else {
            throw Exception("Empty")
        }
    }
}
