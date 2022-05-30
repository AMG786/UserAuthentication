package com.example.userauthentication.domain.repository

import androidx.lifecycle.LiveData
import com.example.retrofit.model.user
import com.example.userauthentication.data.cache.data.User
import retrofit2.Response

interface Repository {

    suspend fun addUser(user: User);

    fun getAllLocalData(): LiveData<List<User>>

    suspend fun getAllRemoteData():Response<List<user>>

    suspend fun user_exist_locallStorage(username:String,password:String):Boolean

}
