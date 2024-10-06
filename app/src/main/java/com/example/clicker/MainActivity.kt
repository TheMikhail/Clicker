package com.example.clicker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    private lateinit var buttonClick: Button
    private lateinit var textScore: TextView
    private lateinit var buttonMulti: Button
    private lateinit var buttonAuto:Button
    private var sizeClick = 1
    private var count = 0
    private val updateCostImprovement = UpdateCostImprovement()
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonClick = findViewById(R.id.buttonScore)
        textScore = findViewById(R.id.text_score)
        buttonMulti = findViewById(R.id.buttonMulti)
        buttonMulti.isEnabled = false
        buttonAuto = findViewById(R.id.buttonAuto)
        buttonAuto.isEnabled = false
        updateView(count)
        updateViewButtonAuto(updateCostImprovement.getUpdateCostAuto())
        displayAndUpdateViewPriceOfUpgradeMulti(updateCostImprovement.getUpdateCostMulti())

        buttonClick.setOnClickListener {
           displayAndUpdateTheNumberOfPoints(countClickOnTap())
        }
        buttonMulti.setOnClickListener{
            multiClick()
        }
        buttonAuto.setOnClickListener {
            displayAndUpdateViewPriceOfUpgradeAuto(updateCostImprovement.getUpdateCostAuto())
        }
    }
    fun countClickOnTap():Int{
        return sizeClick
    }
    fun multiClick(){
        count -= updateCostImprovement.getUpdateCostMulti()
        updateCostImprovement.buyUpdateCountMulti()
        updateView(count)
        displayAndUpdateViewPriceOfUpgradeMulti(updateCostImprovement.getUpdateCostMulti())
        sizeClick *= 2
    }

    fun autoClick(){
        count -= updateCostImprovement.getUpdateCostAuto()
        updateCostImprovement.buyUpdateCountAuto()
        timer.schedule(object :TimerTask(){
            override fun run() {
                count++
                runOnUiThread {
                    updateView(count)
                    updateViewButtonAuto(updateCostImprovement.getUpdateCostAuto())
                }
            }
        },0,1000)
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
        enabledButtonMulti()
        enabledButtonAuto()
    }
    fun enabledButtonMulti(){
        if (count >= updateCostImprovement.getUpdateCostMulti())
        buttonMulti.isEnabled = true
        else buttonMulti.isEnabled = false
    }
    fun displayAndUpdateViewPriceOfUpgradeMulti(count: Int){
        buttonMulti.text = "Купить улучшение 'Кратность' за: $count монет"
    }

    fun updateViewButtonAuto(count: Int){
        buttonAuto.text = "Купить улучшение 'Авто клик' за $count монет"
    }
    fun enabledButtonAuto(){
        if (count >= updateCostImprovement.getUpdateCostAuto())
            buttonAuto.isEnabled = true
        else buttonAuto.isEnabled = false
    }
    fun displayAndUpdateViewPriceOfUpgradeAuto(increment: Int){
        updateViewButtonAuto(increment)
        autoClick()
    }

}