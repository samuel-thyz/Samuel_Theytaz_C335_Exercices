/*
 * Fichier      : AddExpenseScreen.kt
 * Auteur       : Samuel Theytaz
 * Création     : 28.05.2026
 * Modification : 29.05.2026
 *
 * Écran d'ajout d'une dépense, avec validation du formulaire.
 */
package com.example.minidepenses.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun AddExpenseScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val dao = remember {
        AppDatabase.getDatabase(context).expenseDao()
    }

    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Alimentation") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val categories = listOf(
        "Alimentation",
        "Transport",
        "Loisirs",
        "Santé",
        "Autres"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Ajouter une dépense",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom de la dépense") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amountText,
            onValueChange = { amountText = it },
            label = { Text("Montant") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Catégorie")

        Spacer(modifier = Modifier.height(8.dp))

        CategorySelector(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // toDoubleOrNull : renvoie null si le texte n'est pas un nombre valide
                val amount = amountText.toDoubleOrNull()

                // validation : un nom obligatoire et un montant strictement positif
                if (name.isBlank()) {
                    errorMessage = "Le nom est obligatoire."
                    return@Button
                }

                if (amount == null || amount <= 0.0) {
                    errorMessage = "Le montant doit être un nombre supérieur à 0."
                    return@Button
                }

                val expense = Expense(
                    name = name,
                    amount = amount,
                    category = selectedCategory,
                    createdAt = System.currentTimeMillis()
                )

                scope.launch {
                    dao.insertExpense(expense)
                    onBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enregistrer")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Annuler")
        }
    }
}
