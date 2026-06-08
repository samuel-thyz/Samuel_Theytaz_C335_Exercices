/*
 * Fichier      : Exercice1.kt
 * Auteur       : Samuel Theytaz
 * Création     : 04.05.2026
 * Modification : 05.05.2026
 *
 * Exercice 1 - premier programme : affiche un message puis lit le prénom.
 */
package exercices

fun main() {
    println("Hello, Kotlin!")
    println("Quel est votre prénom ?")
    // readLine() récupère ce que l'utilisateur tape au clavier
    val prenom = readLine()
    println("Bonjour, $prenom ! Bienvenue en Kotlin.")
}
