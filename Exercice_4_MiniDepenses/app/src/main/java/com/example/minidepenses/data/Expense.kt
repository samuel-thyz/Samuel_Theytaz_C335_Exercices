/*
 * Fichier      : Expense.kt
 * Auteur       : Samuel Theytaz
 * Création     : 25.05.2026
 * Modification : 26.05.2026
 *
 * Modèle d'une dépense — une ligne de la table Room « expenses ».
 */
package com.example.minidepenses.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val amount: Double,
    val category: String,
    // date d'ajout en millisecondes : sert à trier du plus récent au plus ancien
    val createdAt: Long
)
