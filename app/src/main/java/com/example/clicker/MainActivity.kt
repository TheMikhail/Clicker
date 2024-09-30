package com.example.clicker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var buttonClick: Button
    private lateinit var textScore: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClick = findViewById(R.id.buttonScore)
        textScore = findViewById(R.id.text_view)

        buttonClick.setOnClickListener {
            textScore.text = "Hello"
        }

    }
}