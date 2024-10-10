package com.example.clicker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivityViewModel : ViewModel() {

    private val autoClickManager: UpgradeManager = UpgradeAutoClickManagerImpl
    private val multiClickManager: UpgradeManager = UpgradeMultiClickManagerImpl

    private val _state = MutableStateFlow(
        ViewState(
            0,
            multiClickManager.getCostUpgrade(),
            autoClickManager.getCostUpgrade()
        )
    )
    val state: StateFlow<ViewState> = _state.asStateFlow()

    fun handleClick() {
        val increment = multiClickManager.getLevel()
        _state.update { it.copy(coin = it.coin + increment) }
    }

    fun handleUpgradeMultiClick() {
        //сюда можно дополнительно валидатор
        val cost = multiClickManager.getCostUpgrade()
        multiClickManager.upgrade()
        _state.update {
            it.copy(
                coin = it.coin - cost,
                multiClickCost = multiClickManager.getCostUpgrade()
            )
        }
    }

    fun handleUpgradeAutoClick() {
        //сюда можно дополнительно валидатор
        val cost = autoClickManager.getCostUpgrade()
        autoClickManager.upgrade()
        _state.update {
            it.copy(
                coin = it.coin - cost,
                autoClickCost = autoClickManager.getCostUpgrade()
            )
        }
    }
}