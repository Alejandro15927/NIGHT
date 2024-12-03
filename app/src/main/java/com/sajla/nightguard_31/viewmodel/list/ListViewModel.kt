package com.sajla.nightguard_31.viewmodel.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sajla.nightguard_31.data.models.ListState

class ListViewModel : ViewModel() {
    var state by mutableStateOf(ListState(options = listOf("Opción 1", "Opción 2", "Opción 3")))
        private set

    fun onOptionSelected(option: String) {
        state = state.copy(selectedOption = option)
    }

    fun updateOptions(newOptions: List<String>) {
        state = state.copy(options = newOptions)
    }

    fun resetSelection() {
        state = state.copy(selectedOption = "")
    }
}