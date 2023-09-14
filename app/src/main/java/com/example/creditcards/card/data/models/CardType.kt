package com.example.creditcards.card.data.models

enum class CardType(val value: String) {
    VISA("visa"),
    MASTERCARD("mastercard"),
    AMERICAN_EXPRESS("american_express"),
    DISCOVER("discover"),
    JCB("jcb"),
    FORBRUGSFORENINGEN("forbrugsforeningen"),
    SWITCH("switch"),
    DINERS_CLUB("diners_club"),
    DANKORT("dankort"),
    SOLO("solo"),
    LASER("laser"),
    MAESTRO("maestro"),
}

sealed class Card(
    val id: Int,
    val uid: String,
    val creditCardNumber: String,
    val creditCardExpiryDate: String
) {
    abstract val cardType: CardType
}

sealed class CreditCard(
    open val id: String,
    open val cardNumber: String,
    open val expirationDate: String,
    open val cardType: CardType
) {
    data class Visa(
        override val id: String,
        override val cardNumber: String,
        override val expirationDate: String
    ) : CreditCard(id, cardNumber, expirationDate, CardType.VISA)

    data class Mastercard(
        override val id: String,
        override val cardNumber: String,
        override val expirationDate: String
    ) : CreditCard(id, cardNumber, expirationDate, CardType.MASTERCARD)

    data class AmericanExpress(
        override val id: String,
        override val cardNumber: String,
        override val expirationDate: String
    ) : CreditCard(id, cardNumber, expirationDate, CardType.AMERICAN_EXPRESS)

    data class Discover(
        override val id: String,
        override val cardNumber: String,
        override val expirationDate: String
    ) : CreditCard(id, cardNumber, expirationDate, CardType.DISCOVER)

    data class JCB(
        override val id: String,
        override val cardNumber: String,
        override val expirationDate: String
    ) : CreditCard(id, cardNumber, expirationDate, CardType.JCB)

    // Add similar classes for other card types
}
