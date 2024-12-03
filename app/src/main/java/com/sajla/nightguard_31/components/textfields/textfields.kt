@file:OptIn(ExperimentalMaterial3Api::class)

package com.sajla.nightguard_31.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sajla.nightguard_31.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    ph: String,
    isEmail: Boolean = false,
    isPassword: Boolean = false,
    isThereNext: Boolean = false,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(ph) },
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (isThereNext) ImeAction.Next else ImeAction.Done,
            keyboardType = if (isEmail) KeyboardType.Email else KeyboardType.Text
        ),
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(focusedTextColor = colorResource(id = R.color.purple_text), containerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color.Magenta,
            focusedLabelColor = Color.Magenta,
            unfocusedTextColor = Color.LightGray)
    )
}