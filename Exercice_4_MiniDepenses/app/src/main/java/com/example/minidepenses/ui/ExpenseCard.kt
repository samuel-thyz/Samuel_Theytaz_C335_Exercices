/*
 * Fichier      : ExpenseCard.kt
 * Auteur       : Samuel Theytaz
 * Création     : 27.05.2026
 * Modification : 28.05.2026
 *
 * Carte d'affichage d'une dépense (nom, montant, catégorie) + bouton supprimer.
 */
package com.example.minidepenses.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minidepenses.data.Expense

@Composable
fun ExpenseCard(
    expense: Expense,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = expense.name,
                style = MaterialTheme.typography.titleMedium
            )
            // %.2f : on affiche le montant avec deux décimales (ex. 4.50 CHF)
            Text(text = "%.2f CHF".format(expense.amount))
            Text(text = "Catégorie : ${expense.category}")

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(onClick = onDeleteClick) {
                Text("Supprimer")
            }
        }
    }
}
