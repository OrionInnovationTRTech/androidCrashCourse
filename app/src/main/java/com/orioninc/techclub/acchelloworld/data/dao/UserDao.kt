package com.orioninc.techclub.acchelloworld.data.dao

import androidx.room.*
import com.orioninc.techclub.acchelloworld.data.entity.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): User?

    @Query("DELETE FROM user")
    fun deleteAllUser()
}