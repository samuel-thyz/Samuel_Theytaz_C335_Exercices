/*
 * Fichier      : ExpenseDao.kt
 * Auteur       : Samuel Theytaz
 * Création     : 01.06.2026
 * Modification : 03.06.2026
 *
 * DAO : requêtes sur les dépenses, dont les totaux par catégorie.
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

    @Query("SELECT * FROM expenses WHERE category = :category ORDER BY createdAt DESC")
    fun getExpensesByCategory(category: String): Flow<List<Expense>>

    @Query("SELECT COUNT(*) FROM expenses")
    fun countExpenses(): Flow<Int>

    @Query("SELECT SUM(amount) FROM expenses")
    fun getTotalAmount(): Flow<Double?>

    @Query("SELECT COUNT(*) FROM expenses WHERE category = :category")
    fun countByCategory(category: String): Flow<Int>

    @Query("SELECT SUM(amount) FROM expenses WHERE category = :category")
    fun getTotalByCategory(category: String): Flow<Double?>
}
