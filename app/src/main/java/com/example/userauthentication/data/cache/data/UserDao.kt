package com.example.userauthentication.data.cache.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.userauthentication.data.cache.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE username = :username and password = :password)")
    suspend fun isRowIsExist(username : String,password : String) : Boolean

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readDataUsingFlow(): Flow<List<User>>



/*
    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
*/
}