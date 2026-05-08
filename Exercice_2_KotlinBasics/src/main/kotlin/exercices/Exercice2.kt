/*
 * Fichier      : Exercice2.kt
 * Auteur       : Samuel Theytaz
 * Création     : 04.05.2026
 * Modification : 05.05.2026
 *
 * Exercice 2 - variables et conditions : majeur ou mineur selon l'âge saisi.
 */
package exercices

fun main() {
    println("Entrez votre âge :")
    // null si l'utilisateur n'entre rien ou si ce n'est pas un nombre
    val age = readlnOrNull()?.toIntOrNull()

    if (age == null) {
        println("Âge invalide.")
        return
    }

    if (age >= 18) {
        println("Vous êtes majeur.")
    } else {
        println("Vous êtes mineur.")
    }
}
