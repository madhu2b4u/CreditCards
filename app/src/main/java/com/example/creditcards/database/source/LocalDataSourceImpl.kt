package com.example.creditcards.database.source

import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.database.dao.CreditCardDao
import com.example.creditcards.database.mapper.CardsMapper
import com.example.creditcards.di.qualifiers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalDataSourceImpl @Inject constructor(
    private val dao: CreditCardDao,
    private val cardsMapper: CardsMapper,
    @IO private val context: CoroutineContext
) : LocalDataSource {

    override suspend fun getCards() = withContext(context) {
        val cardEntities = dao.getCardsFromDatabase()
        if (cardEntities != null)
            cardsMapper.to(cardEntities)
        else
            null
    }

    override suspend fun saveCards(cards: List<CardDO>) = withContext(context) {
        val card = cardsMapper.from(cards)
        dao.saveCardsToDatabase(card)
    }

}