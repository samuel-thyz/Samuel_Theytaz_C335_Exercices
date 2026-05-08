/*
 * Fichier      : Exercice4a.kt
 * Auteur       : Samuel Theytaz
 * Création     : 06.05.2026
 * Modification : 07.05.2026
 *
 * Exercice 4 (suite) - manipuler une liste : ajouter, retirer et filtrer.
 */
package exercices

fun main() {
    val fruits = mutableListOf("Pomme", "Banane", "Orange", "Poire")
    println("Liste initiale : $fruits")

    fruits.add("Pêche")
    println("Après ajout de Pêche : $fruits")

    fruits.remove("Banane")
    println("Après suppression de Banane : $fruits")

    // on garde seulement les fruits dont le nom commence par P
    val fruitsAvecP = fruits.filter { it.startsWith("P") }
    println("Fruits commençant par P : $fruitsAvecP")
}
