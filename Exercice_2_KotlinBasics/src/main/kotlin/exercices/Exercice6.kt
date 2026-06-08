/*
 * Fichier      : Exercice6.kt
 * Auteur       : Samuel Theytaz
 * Création     : 07.05.2026
 * Modification : 08.05.2026
 *
 * Exercice 6 - gestion des erreurs : division protégée contre le zéro.
 */
package exercices

fun diviser(a: Int, b: Int): Int? {
    // try/catch : si b vaut 0, on attrape l'erreur au lieu de planter
    return try {
        a / b
    } catch (e: ArithmeticException) {
        println("Erreur : division par zéro.")
        null
    }
}

fun main() {
    println("Entrez le premier nombre :")
    val a = readLine()?.toIntOrNull()

    println("Entrez le second nombre :")
    val b = readLine()?.toIntOrNull()

    if (a == null || b == null) {
        println("Entrée invalide.")
        return
    }

    val resultat = diviser(a, b)
    if (resultat != null) {
        println("$a / $b = $resultat")
    }
}
