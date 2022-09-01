package com.orioninc.techclub.acchelloworld.data.repository

import android.content.Context
import com.orioninc.techclub.acchelloworld.util.AppDatabase
import com.orioninc.techclub.acchelloworld.data.entity.LoginBody
import com.orioninc.techclub.acchelloworld.util.RetrofitClient
import com.orioninc.techclub.acchelloworld.data.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository(val context: Context) {

    suspend fun login(username: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            val body = LoginBody(username, password)

            val response = RetrofitClient.userService.login(body)

            if (response.isSuccessful && response.body() != null) {
                val user = User(0, response.body()!!.token)

                return@withContext user
            } else {
                return@withContext null
            }
        }
    }

    suspend fun persistUser(user: User) {
        withContext(Dispatchers.IO) {
            val userDao = AppDatabase.getInstance(context).userDao()

            userDao.deleteAllUser()
            userDao.insert(user)
        }
    }

    suspend fun autoLoginNew(): Boolean {
        return withContext(Dispatchers.IO) {
            val user = AppDatabase.getInstance(context).userDao().getUser()

            return@withContext user != null
        }
    }

    fun logout() {
        GlobalScope.launch {
            launch(Dispatchers.IO) {
                AppDatabase.getInstance(context).userDao().deleteAllUser()
            }
        }
    }
}