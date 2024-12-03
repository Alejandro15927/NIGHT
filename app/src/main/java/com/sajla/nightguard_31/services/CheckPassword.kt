package com.sajla.nightguard_31.services

class CheckPassword : Validation {

    override
    fun isValid(password: String): Boolean {
        val lenghtValid = password.length >= 8
        val upperCaseValid = password.any { it.isUpperCase() }
        val lowerCaseValid = password.any { it.isLowerCase() }
        val digitValid = password.any { it.isDigit() }
        val specialCharValid = password.any { !it.isLetterOrDigit() }

        return lenghtValid && upperCaseValid && lowerCaseValid && digitValid && specialCharValid

    }

}