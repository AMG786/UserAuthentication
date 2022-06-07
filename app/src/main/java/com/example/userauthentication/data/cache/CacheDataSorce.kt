package com.example.userauthentication.data.cache

import androidx.lifecycle.LiveData
import com.example.retrofit.model.user
import com.example.retrofitdemo.api.RetrofitInstance
import com.example.userauthentication.data.DataSorce
import com.example.userauthentication.data.cache.data.User
import com.example.userauthentication.data.cache.data.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class CacheDataSorce
@Inject
constructor(private val userDao: UserDao) :DataSorce{

    override suspend fun addUser(user:User) {
            userDao.addUser(user)
    }

    override fun getAllLocalData(): LiveData<List<User>> {
            return userDao.readAllData()
    }

    override suspend fun getAllRemoteData(): Response<List<user>> {
        return RetrofitInstance.api.getUsers()
    }

    override suspend fun user_exist_locallStorage(username: String, password: String): Boolean {
         return userDao.isRowIsExist(username,password)
    }

    fun ObjectMapper(o: List<User>):List<user>{
        val u:List<user> = o.map { user(it.id,it.username,it.password) }
        return u
    }

    override fun getDataUsingFlow(): Flow<List<user>> {
        return userDao.readDataUsingFlow().map { it -> ObjectMapper(it) }
    }

}