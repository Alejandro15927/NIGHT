package com.sajla.nightguard_31.services

import org.mindrot.jbcrypt.BCrypt

class EncryptPassword {

    fun HashPassword(password: String): String {

        return BCrypt.hashpw(password, BCrypt.gensalt())

    }

    fun VerifyPassword(password: String, hash: String): Boolean {

        return BCrypt.checkpw(password, hash)

    }

}