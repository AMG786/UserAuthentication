package com.example.userauthentication.data

import androidx.lifecycle.LiveData
import com.example.retrofit.model.user
import com.example.userauthentication.Modules.CacheSorce
import com.example.userauthentication.Modules.RemoteSorce
import com.example.userauthentication.data.cache.data.User
import com.example.userauthentication.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class UserRepository
@Inject
constructor(
    @CacheSorce private val dataSorce1: DataSorce,
    @RemoteSorce private val dataSorce2: DataSorce
): Repository {

    val readAllData: LiveData<List<User>> = dataSorce1.getAllLocalData()

    override suspend fun addUser(user: User){
        dataSorce1.addUser(user) //Local Data
        dataSorce2.addUser(user) //Remote Data
    }

    override fun getAllLocalData(): LiveData<List<User>> {
        return readAllData;
    }

    override suspend fun getAllRemoteData(): Response<List<user>> {
          return dataSorce2.getAllRemoteData()
    }

    override suspend fun user_exist_locallStorage(username: String, password: String): Boolean {
           return dataSorce1.user_exist_locallStorage(username,password)
    }


}