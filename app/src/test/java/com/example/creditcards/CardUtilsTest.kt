package com.example.creditcards

import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.common.CardUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class CardUtilsTest {

    @Test
    fun `formatExpiryDate should format date correctly`() {
        val inputDate = "2023-09-13"
        val expectedOutput = "13/09/2023"

        val formattedDate = CardUtils.formatExpiryDate(inputDate)

        assertEquals(expectedOutput, formattedDate)
    }

    @Test
    fun `formatExpiryDate should handle invalid date gracefully`() {
        val inputDate = "invalid_date"
        val expectedOutput = ""

        val formattedDate = CardUtils.formatExpiryDate(inputDate)

        assertEquals(expectedOutput, formattedDate)
    }

    @Test
    fun `sortCardsByExpiryDate should sort cards by expiry date`() {
        // Arrange: Create a list of CardDO instances with different expiration dates
        val cards = listOf(
            CardDO(
                "2024-05-20",
                "1234-2121-1221-1211",
                "forbrugsforeningen",
                1,
                "32e06962-4567-452e-9870-8c977baeda20"
            ),
            CardDO(
                "2022-08-15",
                "1234-2121-1221-1211",
                "forbrugsforeningen",
                2,
                "32e06962-4567-452e-9870-8c977baeda20"
            ),
            CardDO(
                "2023-09-13",
                "1234-2121-1221-1211",
                "forbrugsforeningen",
                3,
                "32e06962-4567-452e-9870-8c977baeda20"
            )
        )

        val sortedCards = CardUtils.sortCardsByExpiryDate(cards)

        // Assert: Define the expected sorted order
        val expectedSortedCards = listOf(
            CardDO(
                "2022-08-15",
                "1234-2121-1221-1211",
                "forbrugsforeningen",
                2,
                "32e06962-4567-452e-9870-8c977baeda20"
            ),
            CardDO(
                "2023-09-13",
                "1234-2121-1221-1211",
                "forbrugsforeningen",
                3,
                "32e06962-4567-452e-9870-8c977baeda20"
            ),
            CardDO(
                "2024-05-20",
                "1234-2121-1221-1211",
                "forbrugsforeningen",
                1,
                "32e06962-4567-452e-9870-8c977baeda20"
            )
        )

        assertEquals(expectedSortedCards, sortedCards)
    }

    @Test
    fun `formatCardType should remove underscores and convert to uppercase`() {
        val cardType = "visa_card"
        val expectedFormattedCardType = "VISA CARD"

        val formattedCardType = CardUtils.formatCardType(cardType)

        assertEquals(expectedFormattedCardType, formattedCardType)
    }
}
