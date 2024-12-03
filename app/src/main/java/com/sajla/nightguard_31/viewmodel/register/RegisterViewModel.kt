package com.sajla.nightguard_31.viewmodel.register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.sajla.nightguard_31.data.models.RegisterState
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    var showAlert by mutableStateOf(false)

    // Variable privada auth de tipo FirebaseAuth
    private val auth: FirebaseAuth = Firebase.auth

    fun onValue(value: String, type: String) {
        when (type) {
            "username" -> state = state.copy(username = value)
            "email" -> state = state.copy(email = value)
            "password" -> state = state.copy(password = value)
            "confirmPassword" -> state = state.copy(confirmPassword = value)
        }
    }

    fun createUser(username: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            saveUser(username)
                            onSuccess()
                        } else {
                            Log.d("ERROR EN FIREBASE", "Error al crear usuario")
                            showAlert = true
                        }
                    }
            } catch (e: Exception) {
                Log.d("ERROR EN FIREBASE", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    private fun saveUser(username: String){
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        val user = RegisterState(
            userId = id.toString(),
            email = email.toString(),
            username = username
        )

        FirebaseFirestore.getInstance().collection("Users")
            .add(user)
            .addOnSuccessListener {
                Log.d("GUARD", "GUARDO CORRECTAMENTE" +
                        "Firebase")
            }
            .addOnFailureListener {
                Log.d("ERROR EN FIREBASE", "Error al guardar en Firebase")
            }

    }

    fun onValue(value: Boolean, type: String) {
        when (type) {
            "isUsernameValid" -> state = state.copy(isUsernameValid = value)
            "isEmailValid" -> state = state.copy(isEmailValid = value)
            "isPasswordValid" -> state = state.copy(isPasswordValid = value)
            "isConfirmPasswordValid" -> state = state.copy(isConfirmPasswordValid = value)
            "isFormValid" -> state = state.copy(isFormValid = value)
        }
    }

    fun registerUser(onResult: (Boolean) -> Unit) {

        val isSuccess = state.password == state.confirmPassword
        onResult(isSuccess)
    }
}