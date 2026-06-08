/*
 * Fichier      : ExpenseListScreen.kt
 * Auteur       : Samuel Theytaz
 * Création     : 27.05.2026
 * Modification : 29.05.2026
 *
 * Écran principal : liste des dépenses avec suppression confirmée.
 */
package com.example.minidepenses.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.minidepenses.data.AppDatabase
import com.example.minidepenses.data.Expense
import kotlinx.coroutines.launch

@Composable
fun ExpenseListScreen(
    onAddClick: () -> Unit,
    onStatsClick: () -> Unit
) {
    val context = LocalContext.current
    val dao = remember {
        AppDatabase.getDatabase(context).expenseDao()
    }

    val scope = rememberCoroutineScope()
    // on observe la base : la liste se met à jour toute seule après un ajout/suppression
    val expenses by dao.getAllExpenses().collectAsState(initial = emptyList())

    var expenseToDelete by remember { mutableStateOf<Expense?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mini Dépenses",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAddClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ajouter une dépense")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onStatsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Statistiques")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (expenses.isEmpty()) {
            Text("Aucune dépense enregistrée.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = expenses,
                    key = { it.id }
                ) { expense ->
                    ExpenseCard(
                        expense = expense,
                        onDeleteClick = { expenseToDelete = expense }
                    )
                }
            }
        }
    }

    // boîte de dialogue : on ne supprime vraiment qu'après confirmation
    val pendingDelete = expenseToDelete
    if (pendingDelete != null) {
        AlertDialog(
            onDismissRequest = { expenseToDelete = null },
            title = { Text("Supprimer la dépense") },
            text = { Text("Voulez-vous vraiment supprimer cette dépense ?") },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            dao.deleteExpense(pendingDelete)
                        }
                        expenseToDelete = null
                    }
                ) {
                    Text("Supprimer")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { expenseToDelete = null }
                ) {
                    Text("Annuler")
                }
            }
        )
    }
}
