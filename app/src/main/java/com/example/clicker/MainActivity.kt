package com.example.clicker

import android.os.Build.VERSION_CODES.S
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var buttonClick: Button
    private lateinit var textScore: TextView
    private lateinit var buttonMulti: Button
    private var sizeClick = 1
    private var count = 0
    private val updateCostImprovement = UpdateCostImprovement()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonClick = findViewById(R.id.buttonScore)
        textScore = findViewById(R.id.text_score)
        buttonMulti = findViewById(R.id.buttonMulti)
        buttonMulti.isEnabled = false
        updateView(count)
        displayAndUpdateViewPriceOfUpgrade(updateCostImprovement.getUpdateCost())
        buttonClick.setOnClickListener {
           displayAndUpdateTheNumberOfPoints(countClickOnTap())
        }
        buttonMulti.setOnClickListener{
            multiClick()
        }
    }
    fun countClickOnTap():Int{
        return sizeClick
    }
    fun multiClick(){
        count -= updateCostImprovement.getUpdateCost()
        updateCostImprovement.buyUpdateCount()
        updateView(count)
        displayAndUpdateViewPriceOfUpgrade(updateCostImprovement.getUpdateCost())
        sizeClick *= 2
    }

    fun displayAndUpdateTheNumberOfPoints(increment: Int){
        updateScore(increment)
        updateView(count)
    }

    fun updateScore(increment: Int){
        count += increment
    }
    fun updateView(currentScore: Int){
        textScore.text = "Текущий счет: $currentScore"
        enabledButton()
    }
    fun enabledButton(){
        if (count >= updateCostImprovement.getUpdateCost())
        buttonMulti.isEnabled = true
        else buttonMulti.isEnabled = false
    }
    fun displayAndUpdateViewPriceOfUpgrade(count: Int){
        buttonMulti.text = "Купить улучшение 'Кратность' за: $count монет"
    }

}