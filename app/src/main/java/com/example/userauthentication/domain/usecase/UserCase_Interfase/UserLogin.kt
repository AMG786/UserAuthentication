package com.example.userauthentication.domain.usecase.UserCase_Interfase

import androidx.lifecycle.LiveData
import com.example.retrofit.model.user
import com.example.userauthentication.data.cache.data.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserLogin {

    suspend fun  LoginUser(user: User):Boolean

    fun getAlluser(): LiveData<List<User>>
    suspend fun getAllRemoteData():Response<List<user>>

    fun getData1():Flow<List<user>>

    fun getData2():Flow<List<user>>


}