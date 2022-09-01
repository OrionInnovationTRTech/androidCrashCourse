package com.orioninc.techclub.acchelloworld.domain

import android.content.Context
import com.orioninc.techclub.acchelloworld.data.repository.UserRepository

class LoginUseCase(val context: Context) {

    suspend operator fun invoke(username: String, password: String): Boolean {
        val userRepository = UserRepository(context)

        val user = userRepository.login(username, password)

        if (user != null) {
            userRepository.persistUser(user)
            return true
        } else {
            return false
        }
    }
}