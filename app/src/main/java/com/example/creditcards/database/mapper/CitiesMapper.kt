package com.example.creditcards.database.mapper

import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.database.entities.DbCards
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class CardsMapper @Inject constructor(private val gson: Gson) : Mapper<DbCards, List<CardDO>> {
    override fun from(e: List<CardDO>) = DbCards(1, gson.toJson(e))
    override fun to(t: DbCards): List<CardDO> {
        return gson.fromJson(
            t.cards,
            TypeToken.getParameterized(ArrayList::class.java, CardDO::class.java).type
        )
    }
}