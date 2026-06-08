/*
 * Fichier      : CategoryStat.kt
 * Auteur       : Samuel Theytaz
 * Création     : 08.06.2026
 * Modification : 08.06.2026
 *
 * Petit modèle qui regroupe ce dont un graphique a besoin pour une catégorie.
 */
package com.example.minidepenses.data

// regroupe catégorie + total + nombre, prêt à dessiner dans les graphiques
data class CategoryStat(
    val category: String,
    val total: Double,
    val count: Int
)
