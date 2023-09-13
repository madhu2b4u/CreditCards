package com.example.creditcards.database.dao

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.creditcards.database.entities.DbCards

@Database(
    entities = [DbCards::class],
    version = 1,
    exportSchema = false
)
abstract class CreditCardDatabase : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "card.db"

        @Volatile
        private var INSTANCE: CreditCardDatabase? = null

        fun getInstance(@NonNull context: Context): CreditCardDatabase {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            CreditCardDatabase::class.java,
                            DATABASE_NAME
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getCreditCardDao(): CreditCardDao

}