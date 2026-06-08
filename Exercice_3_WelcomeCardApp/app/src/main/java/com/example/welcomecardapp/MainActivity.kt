/*
 * Fichier      : MainActivity.kt
 * Auteur       : Samuel Theytaz
 * Création     : 11.05.2026
 * Modification : 19.05.2026
 *
 * Application Welcome Card : formulaire, carte selon l'humeur et écran de détails.
 */
package com.example.welcomecardapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WelcomeCardApp()
                }
            }
        }
    }
}

@Composable
fun WelcomeCardApp() {
    // navigation maison : "form" pour le formulaire, "details" pour les détails
    var currentScreen by remember { mutableStateOf("form") }
    var userName by remember { mutableStateOf("") }
    var selectedMood by remember { mutableStateOf("Heureux") }
    var showCard by remember { mutableStateOf(false) }

    when (currentScreen) {
        "form" -> {
            WelcomeFormScreen(
                userName = userName,
                onUserNameChange = { userName = it },
                selectedMood = selectedMood,
                onMoodSelected = { selectedMood = it },
                showCard = showCard,
                onCreateCard = { showCard = true },
                onOpenDetails = { currentScreen = "details" }
            )
        }
        "details" -> {
            DetailsScreen(
                userName = userName,
                selectedMood = selectedMood,
                onBack = { currentScreen = "form" }
            )
        }
    }
}

@Composable
fun WelcomeFormScreen(
    userName: String,
    onUserNameChange: (String) -> Unit,
    selectedMood: String,
    onMoodSelected: (String) -> Unit,
    showCard: Boolean,
    onCreateCard: () -> Unit,
    onOpenDetails: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome Card App",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Entrez votre prénom, choisissez votre humeur, puis générez une carte personnalisée.")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = { Text("Votre prénom") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Humeur :")

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Heureux", "Concentré", "Fatigué").forEach { mood ->
                FilterChip(
                    selected = selectedMood == mood,
                    onClick = { onMoodSelected(mood) },
                    label = { Text(mood) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // si le prénom est vide, on prévient avec un Toast au lieu de créer la carte
                if (userName.isBlank()) {
                    Toast.makeText(
                        context,
                        "Veuillez saisir votre prénom",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    onCreateCard()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Créer la carte")
        }

        // la carte n'apparaît qu'après validation et avec un prénom non vide
        if (showCard && userName.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            WelcomeCard(
                userName = userName,
                selectedMood = selectedMood,
                onOpenDetails = onOpenDetails
            )
        }
    }
}

@Composable
fun WelcomeCard(
    userName: String,
    selectedMood: String,
    onOpenDetails: () -> Unit
) {
    val moodMessage = when (selectedMood) {
        "Heureux" -> "Continue à partager cette énergie positive."
        "Concentré" -> "Reste concentré et continue à progresser."
        "Fatigué" -> "Prends une courte pause puis reprends calmement."
        else -> "Bienvenue."
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Bonjour, $userName !",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Humeur : $selectedMood")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Message : $moodMessage")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onOpenDetails) {
                Text("Ouvrir les détails")
            }
        }
    }
}

@Composable
fun DetailsScreen(
    userName: String,
    selectedMood: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Détails du profil",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Prénom : $userName")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Humeur : $selectedMood")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retour")
        }
    }
}
