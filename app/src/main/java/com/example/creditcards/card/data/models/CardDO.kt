package com.example.creditcards.card.data.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CardDO(
    @Expose @SerializedName("credit_card_expiry_date")
    val creditCardExpiryDate: String,
    @Expose @SerializedName("credit_card_number")
    val creditCardNumber: String,
    @Expose @SerializedName("credit_card_type")
    val creditCardType: String,
    @Expose @SerializedName("id")
    val id: Int,
    @Expose @SerializedName("uid")
    val uid: String
)


