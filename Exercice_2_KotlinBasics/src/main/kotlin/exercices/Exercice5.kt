/*
 * Fichier      : Exercice5.kt
 * Auteur       : Samuel Theytaz
 * Création     : 06.05.2026
 * Modification : 07.05.2026
 *
 * Exercice 5 - orienté objet : un compte bancaire avec dépôt et retrait.
 */
package exercices

class CompteBancaire() {
    // le solde se lit de partout mais ne se modifie que dans la classe
    var solde: Double = 0.0
        private set

    fun deposer(montant: Double) {
        if (montant <= 0) {
            println("Le montant doit être positif.")
            return
        }
        solde += montant
        println("Dépôt de $montant €. Nouveau solde : $solde €")
    }

    fun retirer(montant: Double) {
        if (montant <= 0) {
            println("Le montant doit être positif.")
            return
        }
        if (solde >= montant) {
            solde -= montant
            println("Retrait de $montant €. Nouveau solde : $solde €")
        } else {
            println("Solde insuffisant. Solde actuel : $solde €")
        }
    }
}

fun main() {
    val compte = CompteBancaire()
    compte.deposer(100.0)
    compte.deposer(50.0)
    compte.retirer(30.0)
    compte.retirer(200.0)
}
