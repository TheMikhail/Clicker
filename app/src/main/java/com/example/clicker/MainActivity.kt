package com.example.clicker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonClick: Button
    private lateinit var textScore: TextView
    private lateinit var buttonMulti: Button
    private var sizeClick = 1.0
    private var count = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonClick = findViewById(R.id.buttonScore)
        textScore = findViewById(R.id.text_score)
        buttonMulti = findViewById(R.id.buttonMulti)
        buttonMulti.isEnabled = false
        updateView(count)
        buttonClick.setOnClickListener {
           displayAndUpdateTheNumberOfPoints(countClickOnTap())
        }
        buttonMulti.setOnClickListener{
            multiClick()
        }
    }
    fun countClickOnTap():Double{
        return sizeClick
    }
    fun multiClick(){
        count -= UpdateCostImprovement().getUpdateCost()
        UpdateCostImprovement().buyUpdateCount()
        updateView(count)
        sizeClick *= 2
    }

    fun displayAndUpdateTheNumberOfPoints(increment: Double){
        updateScore(increment)
        updateView(count)
    }

    fun updateScore(increment: Double){
        count += increment
    }
    fun updateView(currentScore: Double){
        textScore.text = "Текущий счет: $currentScore"
        enabledButton()
    }
    fun enabledButton(){
        if (count >= UpdateCostImprovement().getUpdateCost())
        buttonMulti.isEnabled = true
        else buttonMulti.isEnabled = false
    }

}