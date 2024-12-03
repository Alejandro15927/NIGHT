package com.sajla.nightguard_31.data.models

data class RegisterState(
    val userId: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isUsernameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isConfirmPasswordValid: Boolean = true,
    val isFormValid: Boolean = false
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "userId" to this.userId,
            "username" to this.username,
            "email" to this.email,
            "password" to this.password
        )
    }
}
