package com.example.userauthentication.domain.usecase.UsercasesImp

import androidx.lifecycle.LiveData
import com.example.retrofit.model.user
import com.example.userauthentication.data.cache.data.User
import com.example.userauthentication.domain.repository.Repository
import com.example.userauthentication.domain.usecase.UserCase_Interfase.UserLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

class Login_UseCase
    @Inject
    constructor(private var repository: Repository):UserLogin
{
     var  readAllData: LiveData<List<User>> =repository.getAllLocalData()

     private var data1: Flow<List<user>> =repository.getdata1()

    override fun getData1(): Flow<List<user>> {
        return data1
    }

    private var data2: Flow<List<user>> = repository.getdata2()

    override fun getData2(): Flow<List<user>> {
        return data2;
    }

    override suspend fun LoginUser(u: User):Boolean {
        return repository.user_exist_locallStorage(u.username,u.password)
    }

    override fun getAlluser(): LiveData<List<User>> {
        return readAllData
    }

    override suspend fun getAllRemoteData(): Response<List<user>> {
        return repository.getAllRemoteData()
    }

}