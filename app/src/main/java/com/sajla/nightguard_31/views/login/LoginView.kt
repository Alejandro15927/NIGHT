package com.sajla.nightguard_31.views.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sajla.nightguard_31.R
import com.sajla.nightguard_31.components.alerts.Alert
import com.sajla.nightguard_31.components.buttons.LoginButton
import com.sajla.nightguard_31.components.images.CustomImage
import com.sajla.nightguard_31.components.textfields.CustomOutlinedTextField
import com.sajla.nightguard_31.data.store.StoreLogin
import com.sajla.nightguard_31.viewmodel.login.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginView(navController: NavController, viewModel: LoginViewModel) {
    val state = viewModel.state
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreLogin(context)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val userEmail = dataStore.getEmail.collectAsState(initial = "")
        CustomImage(image = R.drawable.moon_logo)
        CustomOutlinedTextField(
            value = state.username,
            onValueChange = { viewModel.onValue(it, "username") },
            ph = "Username",
            label = "Username",
            isThereNext = true,
            isEmail = true
        )
        CustomOutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onValue(it, "password") },
            ph = "Password",
            label = "Password",
            isPassword = true
        )
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LoginButton(
                name = "Log in",
                backColor = R.color.teal_200,
                color = R.color.button_color_1
            ) {
                scope.launch {
                    viewModel.login(state.username, state.password) {
                        navController.navigate("Home")
                    }
                }
            }

            Text(
                text = "Or",
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 15.dp)
            )

            LoginButton(
                name = "Sign up",
                backColor = R.color.button_color_1,
                color = R.color.teal_200
            ) {

                navController.navigate("Register")
            }

            if (viewModel.showAlert){
                Alert(title = "Alerta", message = "Usuario y/o Contraseña Incorrectos", confirmText = "Aceptar", onDismiss = { viewModel.closeAlert() }) {

                }
            }

        }

        Spacer(modifier = Modifier.height(15.dp))
        LoginButton(
            name = "Go to Tools",
            backColor = R.color.button_color_1,
            color = R.color.teal_200
        ) {
            navController.navigate("InfoView")
        }

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Forgot your password?",
            color = colorResource(id = R.color.button_color_1),
            modifier = Modifier.clickable {
                throw RuntimeException("Test Crash")
            }
        )
    }
}
