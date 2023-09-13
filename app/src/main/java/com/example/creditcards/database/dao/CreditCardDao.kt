package com.example.creditcards.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.creditcards.database.entities.DbCards

@Dao
abstract class CreditCardDao {
    @Query("SELECT * FROM db_cards")
    abstract suspend fun getCardsFromDatabase(): DbCards?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveCardsToDatabase(cities: DbCards)
}