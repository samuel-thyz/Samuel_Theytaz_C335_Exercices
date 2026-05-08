/*
 * Fichier      : Exercice3.kt
 * Auteur       : Samuel Theytaz
 * Création     : 05.05.2026
 * Modification : 06.05.2026
 *
 * Exercice 3 - fonctions et boucles : affiche les multiples de 3 jusqu'à n.
 */
package exercices

fun afficherMultiplesDe3(n: Int) {
    for (i in 1..n) {
        // un nombre est multiple de 3 quand le reste de sa division par 3 vaut 0
        if (i % 3 == 0) {
            println(i)
        }
    }
}

fun main() {
    println("Entrez un nombre :")
    val n = readLine()?.toIntOrNull() ?: 0
    println("Multiples de 3 jusqu'à $n :")
    afficherMultiplesDe3(n)
}
