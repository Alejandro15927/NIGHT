package com.sajla.nightguard_31.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.views.RegisterView
import com.sajla.nightguard_31.viewmodel.login.LoginViewModel
import com.sajla.nightguard_31.views.main.StartView
import com.sajla.nightguard_31.viewmodel.register.RegisterViewModel
import com.sajla.nightguard_31.views.info.InfoView
import com.sajla.nightguard_31.views.login.LoginView
import com.sajla.nightguard_31.views.main.MainView
import com.sajla.nightguard_31.views.tools.ToolsView

@Composable
fun NavManager(loginViewModel: LoginViewModel, registerViewModel: RegisterViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") {
            LoginView(navController, loginViewModel)
        }
        composable("Register") {
            RegisterView(navController, registerViewModel)
        }
        composable("Home") {
            StartView(navController)
        }
        composable("ToolsView") {
            val context = LocalContext.current
            ToolsView(navController = navController, context = context)
        }
        composable("InfoView") {
            InfoView(navController)
        }
    }
}