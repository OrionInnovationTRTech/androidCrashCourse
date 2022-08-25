package com.orioninc.techclub.acchelloworld

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository(val context: Context) {

    fun login(username: String, password: String, onCompletion: (Boolean) -> Unit) {
        GlobalScope.launch {
            launch(Dispatchers.IO) {
                val body = LoginBody(username, password)

                val response = RetrofitClient.userService.login(body)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        val user = User(0, response.body()!!.token)
                        persistUser(user)

                        onCompletion(true)
                    } else {
                        onCompletion(false)
                    }
                }
            }
        }
    }

    private fun persistUser(user: User) {
        GlobalScope.launch {
            launch (Dispatchers.IO) {

                val userDao = AppDatabase.getInstance(context).userDao()

                userDao.deleteAllUser()
                userDao.insert(user)
            }
        }
    }

    fun autoLogin(onCompletion: (Boolean) -> Unit) {
        GlobalScope.launch {
            launch(Dispatchers.IO) {
                val user = AppDatabase.getInstance(context).userDao().getUser()

                withContext(Dispatchers.Main) {
                    if (user != null)
                        onCompletion(true)
                    else
                        onCompletion(false)
                }
            }
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