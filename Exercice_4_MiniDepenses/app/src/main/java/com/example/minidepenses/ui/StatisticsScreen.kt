/*
 * Fichier      : StatisticsScreen.kt
 * Auteur       : Samuel Theytaz
 * Création     : 28.05.2026
 * Modification : 29.05.2026
 *
 * Écran statistiques : totaux et nombre de dépenses par catégorie.
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.minidepenses.data.AppDatabase

@Composable
fun StatisticsScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val dao = remember {
        AppDatabase.getDatabase(context).expenseDao()
    }

    val totalCount by dao.countExpenses().collectAsState(initial = 0)
    val totalAmount by dao.getTotalAmount().collectAsState(initial = 0.0)

    val foodCount by dao.countByCategory("Alimentation").collectAsState(initial = 0)
    val transportCount by dao.countByCategory("Transport").collectAsState(initial = 0)
    val leisureCount by dao.countByCategory("Loisirs").collectAsState(initial = 0)
    val healthCount by dao.countByCategory("Santé").collectAsState(initial = 0)
    val otherCount by dao.countByCategory("Autres").collectAsState(initial = 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Statistiques",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nombre total de dépenses : $totalCount")
        // ?: 0.0 : si la base est vide, SUM renvoie null, on affiche alors 0.00
        Text("Total dépensé : %.2f CHF".format(totalAmount ?: 0.0))

        Spacer(modifier = Modifier.height(16.dp))

        Text("Alimentation : $foodCount")
        Text("Transport : $transportCount")
        Text("Loisirs : $leisureCount")
        Text("Santé : $healthCount")
        Text("Autres : $otherCount")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retour")
        }
    }
}
