package com.example.clicker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private val clickButton: Button by lazy { findViewById(R.id.buttonScore) }
    private val scoreText: TextView by lazy { findViewById(R.id.text_score) }
    private val upgradeMultiButton: Button by lazy { findViewById(R.id.buttonMulti) }
    private val upgradeAutoButton: Button by lazy { findViewById(R.id.buttonAuto) }

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton.setOnClickListener { viewModel.handleClick() }
        upgradeAutoButton.setOnClickListener { viewModel.handleUpgradeAutoClick() }
        upgradeMultiButton.setOnClickListener { viewModel.handleUpgradeMultiClick() }

        viewModel.state
            .onEach(::render)
            .launchIn(lifecycleScope)
    }

    private fun render(state: ViewState) {
        scoreText.text = "Текущий счет: ${state.coin}"
        upgradeAutoButton.text = "Купить улучшение 'Авто клик' за ${state.autoClickCost} монет"
        upgradeMultiButton.text = "Купить улучшение 'Кратность' за: ${state.multiClickCost} монет"

        upgradeAutoButton.isEnabled = state.coin >= state.autoClickCost
        upgradeMultiButton.isEnabled = state.coin >= state.multiClickCost
    }
}