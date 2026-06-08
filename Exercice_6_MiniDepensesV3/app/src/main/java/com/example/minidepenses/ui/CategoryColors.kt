/*
 * Fichier      : CategoryColors.kt
 * Auteur       : Samuel Theytaz
 * Création     : 08.06.2026
 * Modification : 08.06.2026
 *
 * Une couleur fixe par catégorie, partagée par les barres, le donut et la légende.
 */
package com.example.minidepenses.ui

import androidx.compose.ui.graphics.Color
import com.example.minidepenses.data.ExpenseCategories

// même couleur pour une catégorie partout : sinon l'œil ne fait pas le lien
fun colorForCategory(category: String): Color {
    return when (category) {
        ExpenseCategories.FOOD -> Color(0xFFC46A3A)
        ExpenseCategories.TRANSPORT -> Color(0xFF4A9B5E)
        ExpenseCategories.LEISURE -> Color(0xFF5B7CCB)
        ExpenseCategories.HEALTH -> Color(0xFFC85C6B)
        else -> Color(0xFF9B8700)
    }
}
