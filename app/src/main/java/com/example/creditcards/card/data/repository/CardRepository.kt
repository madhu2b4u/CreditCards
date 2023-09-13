package com.example.creditcards.card.data.repository

import androidx.lifecycle.LiveData
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.common.Result

interface CardRepository {

    suspend fun getCards(size: Int): LiveData<Result<List<CardDO>>>

}