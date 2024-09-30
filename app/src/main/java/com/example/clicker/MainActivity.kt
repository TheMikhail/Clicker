package com.example.clicker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonClick: Button
    private lateinit var textScore: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClick = findViewById(R.id.buttonScore)
        textScore = findViewById(R.id.text_score)

        buttonClick.setOnClickListener {
            countClick()
        }

    }

    fun countClick() {
        val countString = textScore.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++

        textScore.text = count.toString()
    }
}