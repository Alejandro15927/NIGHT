package com.example.myapplication.views

import androidx.compose.foundation.clickable



import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.rememberCoroutineScope

import androidx.navigation.NavController
import com.sajla.nightguard_31.R
import com.sajla.nightguard_31.components.buttons.LoginButton
import com.sajla.nightguard_31.components.images.CustomImage
import com.sajla.nightguard_31.components.textfields.CustomOutlinedTextField

import com.sajla.nightguard_31.viewmodel.register.RegisterViewModel



@Composable
fun RegisterView(navController: NavController, viewModel: RegisterViewModel) {

    val state = viewModel.state
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomImage(image = R.drawable.moon_logo)


        CustomOutlinedTextField(
            value = state.username,
            onValueChange = { viewModel.onValue(it, "username") },
            ph = "Username",
            label = "Username",
            isThereNext = true
        )


        CustomOutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onValue(it, "email") },
            ph = "Email",
            label = "Email",
            isEmail = true,
            isThereNext = true
        )


        CustomOutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onValue(it, "password") },
            ph = "Password",
            label = "Password",
            isPassword = true,
            isThereNext = true
        )


        CustomOutlinedTextField(
            value = state.confirmPassword,
            onValueChange = { viewModel.onValue(it, "confirmPassword") },
            ph = "Confirm Password",
            label = "Confirm Password",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(15.dp))


        LoginButton(name = "Register", backColor = R.color.teal_200, color = R.color.button_color_1) {
            if (state.password == state.confirmPassword) {
                viewModel.registerUser { success ->
                    if (success) {
                        viewModel.createUser( state.username, state.email, state.password) {
                            navController.navigate("Home")
                        }
                    } else {
                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }


        Spacer(modifier = Modifier.height(15.dp))


        Text(
            text = "Ya tienes una cuenta? Ingresa pe gilaso",
            color = colorResource(id = R.color.button_color_1),
            modifier = Modifier.clickable {
                navController.navigate("Login")
            }
        )
    }
}
