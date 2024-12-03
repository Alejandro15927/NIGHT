    package com.sajla.nightguard_31.components.hour

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun CustomTimePicker(
    context: Context,
    modifier: Modifier = Modifier,
    initialHour: Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
    initialMinute: Int = Calendar.getInstance().get(Calendar.MINUTE),
    onTimeSelected: (String) -> Unit
) {
    val timeState = rememberSaveable { mutableStateOf("$initialHour:$initialMinute") }

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hour: Int, minute: Int ->
                val formattedTime = String.format("%02d:%02d", hour, minute)
                timeState.value = formattedTime
                onTimeSelected(formattedTime)
            },
            initialHour,
            initialMinute,
            true
        )
    }

    TextButton(
        onClick = { timePickerDialog.show() },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Hora seleccionada: ${timeState.value}",
            fontWeight = FontWeight.Bold
        )
    }
}
