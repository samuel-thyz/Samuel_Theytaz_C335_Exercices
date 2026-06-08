/*
 * Fichier      : StatisticsScreen.kt
 * Auteur       : Samuel Theytaz
 * Création     : 04.06.2026
 * Modification : 05.06.2026
 *
 * Statistiques : totaux et nombre de dépenses par catégorie.
 */
package com.example.minidepenses.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

    val foodCount by dao.countByCategory(ExpenseCategories.FOOD).collectAsState(initial = 0)
    val foodTotal by dao.getTotalByCategory(ExpenseCategories.FOOD).collectAsState(initial = 0.0)

    val transportCount by dao.countByCategory(ExpenseCategories.TRANSPORT).collectAsState(initial = 0)
    val transportTotal by dao.getTotalByCategory(ExpenseCategories.TRANSPORT).collectAsState(initial = 0.0)

    val leisureCount by dao.countByCategory(ExpenseCategories.LEISURE).collectAsState(initial = 0)
    val leisureTotal by dao.getTotalByCategory(ExpenseCategories.LEISURE).collectAsState(initial = 0.0)

    val healthCount by dao.countByCategory(ExpenseCategories.HEALTH).collectAsState(initial = 0)
    val healthTotal by dao.getTotalByCategory(ExpenseCategories.HEALTH).collectAsState(initial = 0.0)

    val otherCount by dao.countByCategory(ExpenseCategories.OTHER).collectAsState(initial = 0)
    val otherTotal by dao.getTotalByCategory(ExpenseCategories.OTHER).collectAsState(initial = 0.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Statistiques",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ?: 0.0 : si la base est vide, SUM renvoie null, on affiche alors 0.00
        Text("Total global : %.2f CHF".format(totalAmount ?: 0.0))
        Text("Nombre total de dépenses : $totalCount")

        Spacer(modifier = Modifier.height(8.dp))

        CategoryStatItem(
            title = ExpenseCategories.FOOD,
            count = foodCount,
            total = foodTotal
        )

        CategoryStatItem(
            title = ExpenseCategories.TRANSPORT,
            count = transportCount,
            total = transportTotal
        )

        CategoryStatItem(
            title = ExpenseCategories.LEISURE,
            count = leisureCount,
            total = leisureTotal
        )

        CategoryStatItem(
            title = ExpenseCategories.HEALTH,
            count = healthCount,
            total = healthTotal
        )

        CategoryStatItem(
            title = ExpenseCategories.OTHER,
            count = otherCount,
            total = otherTotal
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retour")
        }
    }
}

@Composable
fun CategoryStatItem(
    title: String,
    count: Int,
    total: Double?
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "${iconForCategory(title)} $title",
                style = MaterialTheme.typography.titleMedium
            )
            Text("Nombre : $count")
            Text("Total : %.2f CHF".format(total ?: 0.0))
        }
    }
}
