/*
 * Fichier      : ExpenseCard.kt
 * Auteur       : Samuel Theytaz
 * Création     : 03.06.2026
 * Modification : 04.06.2026
 *
 * Carte d'une dépense (nom, catégorie avec icône, montant) + bouton supprimer.
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
import com.example.minidepenses.data.iconForCategory

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
            // on préfixe la catégorie d'une petite icône (emoji)
            Text(text = "${iconForCategory(expense.category)} ${expense.category}")
            Text(text = "%.2f CHF".format(expense.amount))

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(onClick = onDeleteClick) {
                Text("Supprimer")
            }
        }
    }
}
