/*
 * Fichier      : AppDatabase.kt
 * Auteur       : Samuel Theytaz
 * Création     : 01.06.2026
 * Modification : 02.06.2026
 *
 * Base de données Room (singleton) donnant accès au DAO.
 */
package com.example.minidepenses.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Expense::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        // Singleton : une seule instance de la base pour toute l'application
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "expenses.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
