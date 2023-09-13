package com.example.creditcards.card.domain

import androidx.lifecycle.LiveData
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.common.Result


interface CardUseCase {

    suspend fun getCards(size: Int): LiveData<Result<List<CardDO>>>
}