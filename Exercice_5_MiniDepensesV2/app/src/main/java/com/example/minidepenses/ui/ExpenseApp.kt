/*
 * Fichier      : ExpenseApp.kt
 * Auteur       : Samuel Theytaz
 * Création     : 02.06.2026
 * Modification : 03.06.2026
 *
 * Navigation maison entre les écrans liste / ajout / statistiques.
 */
package com.example.minidepenses.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ExpenseApp() {
    // navigation maison : "list", "add" ou "stats"
    var currentScreen by remember { mutableStateOf("list") }

    when (currentScreen) {
        "list" -> {
            ExpenseListScreen(
                onAddClick = { currentScreen = "add" },
                onStatsClick = { currentScreen = "stats" }
            )
        }
        "add" -> {
            AddExpenseScreen(
                onBack = { currentScreen = "list" }
            )
        }
        "stats" -> {
            StatisticsScreen(
                onBack = { currentScreen = "list" }
            )
        }
    }
}
