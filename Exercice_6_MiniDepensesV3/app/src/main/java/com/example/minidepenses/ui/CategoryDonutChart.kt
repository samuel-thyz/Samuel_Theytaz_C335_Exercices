/*
 * Fichier      : CategoryDonutChart.kt
 * Auteur       : Samuel Theytaz
 * Création     : 08.06.2026
 * Modification : 08.06.2026
 *
 * Graphique en anneau dessiné avec Canvas et drawArc.
 */
package com.example.minidepenses.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.minidepenses.data.CategoryStat

@Composable
fun CategoryDonutChart(
    stats: List<CategoryStat>,
    modifier: Modifier = Modifier
) {
    val total = stats.sumOf { it.total }

    // rien à dessiner si le total est nul : on évite aussi une division par zéro
    if (total <= 0.0) {
        Text("Aucune donnée à afficher.")
        return
    }

    val parts = stats.filter { it.total > 0.0 }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Canvas(modifier = Modifier.size(160.dp)) {
            val stroke = 40f
            val inset = stroke / 2
            val arcSize = Size(size.width - stroke, size.height - stroke)
            var startAngle = -90f
            parts.forEach { stat ->
                val sweep = (stat.total / total * 360.0).toFloat()
                drawArc(
                    color = colorForCategory(stat.category),
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    topLeft = Offset(inset, inset),
                    size = arcSize,
                    style = Stroke(width = stroke)
                )
                // on avance l'angle de départ, sinon toutes les parts se superposent
                startAngle += sweep
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            parts.forEach { stat ->
                val percent = (stat.total / total * 100).toInt()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(colorForCategory(stat.category))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${stat.category} ($percent%)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
