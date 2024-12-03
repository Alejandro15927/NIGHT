package com.sajla.nightguard_31.services

class VerifyCredentials () {

    fun verifyCredentials(username: String, password: String): Boolean {

        val validUsername = username == "admin"
        val validPassword = password == "password"
        return validUsername && validPassword

    }

}