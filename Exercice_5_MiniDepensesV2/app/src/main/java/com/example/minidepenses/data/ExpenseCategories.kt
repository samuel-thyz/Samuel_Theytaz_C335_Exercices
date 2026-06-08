/*
 * Fichier      : ExpenseCategories.kt
 * Auteur       : Samuel Theytaz
 * Création     : 01.06.2026
 * Modification : 02.06.2026
 *
 * Source unique des catégories de dépenses (+ une icône par catégorie).
 */
package com.example.minidepenses.data

object ExpenseCategories {
    const val ALL = "Toutes"
    const val FOOD = "Alimentation"
    const val TRANSPORT = "Transport"
    const val LEISURE = "Loisirs"
    const val HEALTH = "Santé"
    const val OTHER = "Autres"

    // filters : pour filtrer la liste (avec "Toutes") - inputCategories : pour le formulaire
    val filters = listOf(ALL, FOOD, TRANSPORT, LEISURE, HEALTH, OTHER)

    val inputCategories = listOf(FOOD, TRANSPORT, LEISURE, HEALTH, OTHER)
}

fun iconForCategory(category: String): String {
    return when (category) {
        ExpenseCategories.FOOD -> "🍔"
        ExpenseCategories.TRANSPORT -> "🚌"
        ExpenseCategories.LEISURE -> "🎮"
        ExpenseCategories.HEALTH -> "💊"
        else -> "📦"
    }
}
