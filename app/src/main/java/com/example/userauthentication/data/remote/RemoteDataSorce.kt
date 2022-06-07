package com.example.userauthentication.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit.model.user
import com.example.retrofitdemo.api.RetrofitInstance
import com.example.userauthentication.data.DataSorce
import com.example.userauthentication.data.cache.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
class RemoteDataSorce
@Inject
constructor() : DataSorce {

    override suspend fun addUser(USER: User) {
        val u:user=user(USER.id,USER.username,USER.password)
        RetrofitInstance.api.pushPost(u)
    }

    override fun getAllLocalData(): LiveData<List<User>> {
        val l:LiveData<List<User>> =  MutableLiveData<List<User>>()
        return l
    }

    override suspend fun getAllRemoteData(): Response<List<user>> {
        return RetrofitInstance.api.getUsers()
    }

    override suspend fun user_exist_locallStorage(username: String, password: String): Boolean {
        return true;
    }

    override fun getDataUsingFlow(): Flow<List<user>> {

        return flow{
           val b=RetrofitInstance.api.getUsersUsingFlow()
           emit(b)
        }.flowOn(Dispatchers.Default)

    }



}