package com.example.clicker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private lateinit var buttonClick: Button
    private lateinit var textScore: TextView
    private lateinit var buttonMulti: Button
    private lateinit var buttonAuto:Button
    private var sizeClick = 1
    private var count = 0
    private val updateCostImprovement = UpdateCostImprovement()
    private val timer = Timer()
    companion object{
        private const val TAG = "MainActivity"
        private const val KEY_COUNT = "count"
        private const val KEY_COUNT_UPDATE_MULTI = "multi"
        private const val KEY_COUNT_UPDATE_AUTO = "auto"
        private const val KEY_COUNT_UPDATE_SIZE = "sizeClick"
        private const val KEY_TIMER_LAST_RUN = "timer"
        private const val KEY_MULTIPLIER = "multiplier"
    }

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
        updateViewButtonMulti(updateCostImprovement.getUpdateCostMulti())

        buttonClick.setOnClickListener {
           displayAndUpdateTheNumberOfPoints(countClickOnTap())
        }
        buttonMulti.setOnClickListener{
            displayAndUpdateViewPriceOfUpgradeMulti(updateCostImprovement.getUpdateCostMulti())
        }
        buttonAuto.setOnClickListener {
            displayAndUpdateViewPriceOfUpgradeAuto(updateCostImprovement.getUpdateCostAuto())
        }

        if (savedInstanceState != null){
            count = savedInstanceState.getInt(KEY_COUNT)
            updateCostImprovement.upgradeCountAuto = savedInstanceState.getInt(KEY_COUNT_UPDATE_AUTO)
            updateCostImprovement.upgradeCountMulti = savedInstanceState.getInt(KEY_COUNT_UPDATE_MULTI)
            sizeClick = savedInstanceState.getInt(KEY_COUNT_UPDATE_SIZE)
            multiplier = savedInstanceState.getInt(KEY_MULTIPLIER)
            updateView(count)
            updateViewButtonMulti(updateCostImprovement.getUpdateCostMulti())
            updateViewButtonAuto(updateCostImprovement.getUpdateCostAuto())

            Log.i(TAG,"")
            Log.i(TAG,"multiplier = $multiplier")
            Log.i(TAG, "updateCountAuto = ${updateCostImprovement.upgradeCountAuto}")
            Log.i(TAG, "size click = ${sizeClick}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"onSaveInstanceState")
        outState.putInt(KEY_COUNT,count)
        outState.putInt(KEY_COUNT_UPDATE_MULTI, updateCostImprovement.upgradeCountMulti)
        outState.putInt(KEY_COUNT_UPDATE_AUTO, updateCostImprovement.upgradeCountAuto)
        outState.putInt(KEY_COUNT_UPDATE_SIZE,sizeClick)
        outState.putLong(KEY_TIMER_LAST_RUN,lastRunTime)

        outState.putInt(KEY_MULTIPLIER, multiplier)
    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
    fun countClickOnTap():Int{
        return sizeClick
    }
    fun multiClick(){
        count -= updateCostImprovement.getUpdateCostMulti()
        updateCostImprovement.buyUpdateCountMulti()
        updateView(count)
        updateViewButtonMulti(updateCostImprovement.getUpdateCostMulti())
        sizeClick *= 2
    }

    fun buyingMultiClick(){

    }
    private var lastRunTime: Long = 0
    private var multiplier: Int = 0
    fun autoClick(){
        count -= updateCostImprovement.getUpdateCostAuto()
        updateCostImprovement.buyUpdateCountAuto()
        multiplier++
        timer.schedule(object :TimerTask(){
            override fun run() {
                count += multiplier
                runOnUiThread {
                    updateView(count)
                    updateViewButtonAuto(updateCostImprovement.getUpdateCostAuto())
                }
                lastRunTime = System.currentTimeMillis()
            }
        },0,1000)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt(KEY_COUNT)
        lastRunTime = savedInstanceState.getLong(KEY_TIMER_LAST_RUN)
        multiplier = savedInstanceState.getInt(KEY_MULTIPLIER)
        if (lastRunTime > 0){
            timer.schedule(object :TimerTask(){
                override fun run() {
                    count += multiplier
                    runOnUiThread {
                        updateView(count)
                        updateViewButtonAuto(updateCostImprovement.getUpdateCostAuto())
                    }
                    lastRunTime = System.currentTimeMillis()
                }
            },0,1000)
        }
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
    fun displayAndUpdateViewPriceOfUpgradeMulti(increment: Int){
        updateViewButtonMulti(increment)
        multiClick()
        buyingMultiClick()
    }
    fun updateViewButtonMulti(count: Int){
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