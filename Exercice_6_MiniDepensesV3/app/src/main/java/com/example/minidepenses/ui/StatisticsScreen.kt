/*
 * Fichier      : StatisticsScreen.kt
 * Auteur       : Samuel Theytaz
 * Création     : 08.06.2026
 * Modification : 08.06.2026
 *
 * Tableau de bord : cartes de résumé, graphique en barres et anneau.
 */
package com.example.minidepenses.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.example.minidepenses.data.CategoryStat
import com.example.minidepenses.data.ExpenseCategories
import com.example.minidepenses.data.iconForCategory

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
    val averageAmount by dao.getAverageAmount().collectAsState(initial = 0.0)

    val expenses by dao.getAllExpenses().collectAsState(initial = emptyList())

    // on regroupe les dépenses par catégorie (total + nombre) pour les graphiques
    val stats = ExpenseCategories.inputCategories.map { category ->
        val items = expenses.filter { it.category == category }
        CategoryStat(
            category = category,
            total = items.sumOf { it.amount },
            count = items.size
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Statistiques",
            style = MaterialTheme.typography.headlineMedium
        )

        // base vide : message clair, pas de graphique ni de division par zéro
        if (totalCount == 0) {
            Text("Aucune dépense enregistrée.")
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatSummaryCard(
                    title = "Total",
                    value = "%.2f".format(totalAmount ?: 0.0),
                    subtitle = "CHF",
                    modifier = Modifier.weight(1f)
                )
                StatSummaryCard(
                    title = "Nombre",
                    value = "$totalCount",
                    subtitle = "dépenses",
                    modifier = Modifier.weight(1f)
                )
                StatSummaryCard(
                    title = "Moyenne",
                    value = "%.2f".format(averageAmount ?: 0.0),
                    subtitle = "CHF",
                    modifier = Modifier.weight(1f)
                )
            }

            Text(
                text = "Total par catégorie",
                style = MaterialTheme.typography.titleMedium
            )
            CategoryBarChart(stats = stats)

            Text(
                text = "Répartition",
                style = MaterialTheme.typography.titleMedium
            )
            CategoryDonutChart(stats = stats)

            Text(
                text = "Détail par catégorie",
                style = MaterialTheme.typography.titleMedium
            )
            stats.forEach { stat ->
                CategoryStatItem(stat = stat)
            }
        }

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retour")
        }
    }
}

@Composable
fun StatSummaryCard(
    title: String,
    value: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CategoryStatItem(
    stat: CategoryStat,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "${iconForCategory(stat.category)} ${stat.category}",
                style = MaterialTheme.typography.titleMedium
            )
            Text("Nombre : ${stat.count}")
            Text("Total : %.2f CHF".format(stat.total))
        }
    }
}
