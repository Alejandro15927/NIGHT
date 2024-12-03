package com.sajla.nightguard_31.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LoginButton(name: String, backColor: Int, color: Int, onClick: () -> Unit) {
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
        contentColor = colorResource(id = color),
        containerColor = colorResource(id = backColor)
    )) {
        Text(text = name, fontWeight = FontWeight.Bold)
    }
}