/*
 * Fichier      : ExpenseDao.kt
 * Auteur       : Samuel Theytaz
 * Création     : 25.05.2026
 * Modification : 27.05.2026
 *
 * DAO : les requêtes autorisées sur la table des dépenses.
 */
package com.example.minidepenses.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    // Flow : Compose observe ce résultat et se rafraîchit quand la base change
    @Query("SELECT * FROM expenses ORDER BY createdAt DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT COUNT(*) FROM expenses")
    fun countExpenses(): Flow<Int>

    @Query("SELECT SUM(amount) FROM expenses")
    fun getTotalAmount(): Flow<Double?>

    @Query("SELECT COUNT(*) FROM expenses WHERE category = :category")
    fun countByCategory(category: String): Flow<Int>
}
