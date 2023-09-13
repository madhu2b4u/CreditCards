package com.example.creditcards.card.data.remote.source

import com.example.creditcards.card.data.models.CardDO


interface CardRemoteDataSource {

    suspend fun getCards(size: Int): List<CardDO>
}