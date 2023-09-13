package com.example.creditcards.database.source

import com.example.creditcards.card.data.models.CardDO

interface LocalDataSource {

    suspend fun getCards(): List<CardDO>?

    suspend fun saveCards(cards: List<CardDO>)
}