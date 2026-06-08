/*
 * Fichier      : MainActivity.kt
 * Auteur       : Samuel Theytaz
 * Création     : 27.04.2026
 * Modification : 28.04.2026
 *
 * Écran principal de l'application : affiche le message de bienvenue.
 */
package com.example.helloworld

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // On affiche la mise en page décrite dans activity_main.xml
        setContentView(R.layout.activity_main)

        // On va chercher le TextView du layout pour y mettre notre texte
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Bonjour, Android !"
    }
}
