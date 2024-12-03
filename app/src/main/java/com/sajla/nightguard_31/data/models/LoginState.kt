package com.sajla.nightguard_31.data.models

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isUsernameCorrect: Boolean = false,
    val isPasswordCorrect: Boolean = false,
    val isCredentialsCorrect: Boolean = false
)
