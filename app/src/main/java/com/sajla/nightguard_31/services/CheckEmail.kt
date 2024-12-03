package com.sajla.nightguard_31.services

class CheckEmail : Validation{

    override
    fun isValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

}