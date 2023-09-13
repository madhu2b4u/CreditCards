package com.example.creditcards.common

import com.example.creditcards.card.data.models.CardDO
import java.text.SimpleDateFormat
import java.util.Locale

object CardUtils {
    private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private val outputDateFormat = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())

    fun formatExpiryDate(expiryDate: String): String {
        return try {
            val parsedDate = inputDateFormat.parse(expiryDate)
            parsedDate?.let { outputDateFormat.format(it) } ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    fun sortCardsByExpiryDate(cards: List<CardDO>): List<CardDO> {
        return cards.sortedBy { card ->
            inputDateFormat.parse(card.creditCardExpiryDate)
        }
    }

    fun formatCardType(cardType: String): String {
        // Remove underscores and convert to uppercase
        return cardType.replace("_", " ").uppercase(Locale.getDefault())
    }
}