package com.sajla.nightguard_31.viewmodel.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.sajla.nightguard_31.data.models.LoginState
import com.sajla.nightguard_31.data.models.RegisterState
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    var showAlert by mutableStateOf(false)

    // Variable privada auth de tipo FirebaseAuth
    private val auth: FirebaseAuth = Firebase.auth

    // Función login para comprobar las credenciasl que recibe un email y una contraseña, y una función onSuccess
    // Se activo el servicio de inicio de sesión por correo y contraseña
    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            onSuccess()
                        } else {
                            Log.d("ERROR EN FIREBASE", "Usuario y contraseña incorrecta")
                            showAlert = true
                        }
                    }
            } catch (e: Exception) {
                Log.d("ERROR EN JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    fun closeAlert(){
        showAlert = false
    }

    fun onValue(value: String, type: String) {
        when (type) {
            "username" -> {
                state = state.copy(username = value)
            }

            "password" -> {
                state = state.copy(password = value)
            }
        }
    }


    fun onValue(value: Boolean, type: String) {
        when (type) {
            "isUsernameCorrect" -> {
                state = state.copy(isUsernameCorrect = value)
            }

            "isPasswordCorrect" -> {
                state = state.copy(isPasswordCorrect = value)

            }

            "isCredentialsCorrect" -> {
                state = state.copy(isCredentialsCorrect = value)
            }
        }
    }
}