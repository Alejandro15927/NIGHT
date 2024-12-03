package com.sajla.nightguard_31

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.sajla.nightguard_31.navigation.NavManager
import com.sajla.nightguard_31.viewmodel.login.LoginViewModel
import com.sajla.nightguard_31.viewmodel.register.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginViewModel : LoginViewModel by viewModels()
        val registerViewModel : RegisterViewModel by viewModels()
        setContent {
            NavManager(loginViewModel, registerViewModel)
        }
    }
}

