package com.example.creditcards.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db_cards")
data class DbCards(
    @PrimaryKey val id: Int,
    val cards: String
)

