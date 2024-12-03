package com.sajla.nightguard_31.viewmodel.switch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sajla.nightguard_31.data.models.SwitchState

class SwitchViewModel : ViewModel() {
    var state = mutableStateOf(SwitchState())
        private set

    fun toggleSwitch() {
        state.value = state.value.copy(isOn = !state.value.isOn)
    }
}