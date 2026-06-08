/*
 * Fichier      : Exercice4.kt
 * Auteur       : Samuel Theytaz
 * Création     : 05.05.2026
 * Modification : 06.05.2026
 *
 * Exercice 4 - collections : garde les dépenses au-dessus de 20 € et fait la somme.
 */
package exercices

fun main() {
    val depenses = listOf(5.0, 15.0, 25.0, 50.0, 8.0, 20.0, 100.0)

    // on ne garde que les dépenses strictement supérieures à 20
    val grossesDepenses = depenses.filter { it > 20.0 }
    val total = grossesDepenses.sum()

    println("Toutes les dépenses : $depenses")
    println("Dépenses > 20 € : $grossesDepenses")
    println("Total : $total €")
}
