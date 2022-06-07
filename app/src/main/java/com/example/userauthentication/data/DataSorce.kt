package com.example.userauthentication.data

import androidx.lifecycle.LiveData
import com.example.retrofit.model.user
import com.example.userauthentication.data.cache.data.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DataSorce {

    suspend fun addUser(user: User);

    fun getAllLocalData(): LiveData<List<User>>;

    suspend fun getAllRemoteData():Response<List<user>>

    suspend fun user_exist_locallStorage(username:String,password:String):Boolean

    fun getDataUsingFlow(): Flow<List<user>>

}