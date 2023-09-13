package com.example.creditcards.card.data.remote.services

import com.example.creditcards.card.data.models.CardDO
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardService {

    @GET("credit_cards")
    fun getCardAsync(@Query("size") size: Int): Deferred<Response<List<CardDO>>>

}