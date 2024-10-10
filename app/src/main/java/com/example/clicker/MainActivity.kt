package com.example.clicker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    private lateinit var buttonClick: Button
    private lateinit var textScore: TextView
    private lateinit var buttonMulti: Button
    private lateinit var buttonAuto: Button

    private val viewModel: MainActivityViewModel by viewModels()
    private val updateCostImprovement = UpdateCostImprovement()

    companion object {
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

        buttonClick.setOnClickListener {
            displayAndUpdateTheNumberOfPoints(viewModel.clickOnTap())
        }
        buttonMulti.setOnClickListener {
            Log.d("bMulti","Pushing")
            displayAndUpdateViewPriceOfUpgradeMulti(updateCostImprovement.getUpdateCostMulti())
        }
        buttonAuto.setOnClickListener {
            displayAndUpdateViewPriceOfUpgradeAuto(updateCostImprovement.getUpdateCostAuto())
        }

        viewModel.countMoney
            .onEach { updateView(it) }
            .launchIn(lifecycleScope)
    }

    override fun onStart() {
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

    /*fun multiClick() {
        count -= updateCostImprovement.getUpdateCostMulti()
        updateCostImprovement.buyUpdateCountMulti()
        sizeClick *= 2
    }*/

    fun displayAndUpdateTheNumberOfPoints(increment: Int) {
        updateScore(increment)
    }

    fun updateScore(increment: Int) {
        viewModel.handleClickAction(increment)
    }

    fun updateView(currentScore: Int) {
        textScore.text = "Текущий счет: $currentScore"
        if (currentScore >= updateCostImprovement.getUpdateCostMulti()){
            buttonMulti.isEnabled = true
        }else buttonMulti.isEnabled = false
        if (currentScore >= updateCostImprovement.getUpdateCostAuto()){
                buttonAuto.isEnabled = true
        }else buttonAuto.isEnabled = false
        buttonMulti.text = "Купить улучшение 'Кратность' за: " +
                "${updateCostImprovement.getUpdateCostMulti()} монет"
        buttonAuto.text = "Купить улучшение 'Авто клик' за " +
                "${updateCostImprovement.getUpdateCostAuto()} монет"
    }

    fun displayAndUpdateViewPriceOfUpgradeMulti(increment: Int) {
       // multiClick()
        viewModel.handleButtonMulti()
    }

    fun displayAndUpdateViewPriceOfUpgradeAuto(increment: Int) {

    }

}