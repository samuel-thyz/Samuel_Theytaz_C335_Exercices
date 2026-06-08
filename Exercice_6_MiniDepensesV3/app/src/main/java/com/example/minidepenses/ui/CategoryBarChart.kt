/*
 * Fichier      : CategoryBarChart.kt
 * Auteur       : Samuel Theytaz
 * Création     : 08.06.2026
 * Modification : 08.06.2026
 *
 * Graphique en barres fait main (Column / Row / Box), sans bibliothèque externe.
 */
package com.example.minidepenses.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minidepenses.data.CategoryStat
import com.example.minidepenses.data.iconForCategory

@Composable
fun CategoryBarChart(
    stats: List<CategoryStat>,
    modifier: Modifier = Modifier
) {
    // la plus grosse dépense donne l'échelle : sa barre remplit toute la largeur
    val maxTotal = stats.maxOfOrNull { it.total } ?: 0.0

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stats.forEach { stat ->
            // largeur de la barre = part par rapport au maximum (et garde anti division par zéro)
            val fraction = if (maxTotal > 0.0) {
                (stat.total / maxTotal).toFloat()
            } else {
                0f
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${iconForCategory(stat.category)} ${stat.category}",
                    modifier = Modifier.width(116.dp),
                    style = MaterialTheme.typography.bodyMedium
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(20.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction)
                            .fillMaxHeight()
                            .background(colorForCategory(stat.category))
                    )
                }

                Text(
                    text = "%.2f CHF".format(stat.total),
                    modifier = Modifier
                        .width(88.dp)
                        .padding(start = 8.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
