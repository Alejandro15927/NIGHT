package com.sajla.nightguard_31.viewmodel.time

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sajla.nightguard_31.data.models.HourState

class TimeViewModel : ViewModel() {
    var state by mutableStateOf(HourState())
        private set

    fun onTimeSelected(hour: Int, minute: Int) {
        state = state.copy(hour = hour, minute = minute)
    }

    fun resetTime() {
        state = HourState()
    }
}