package com.example.userauthentication.domain.usecase.UsercasesImp

import androidx.lifecycle.LiveData
import com.example.userauthentication.data.cache.data.User
import com.example.userauthentication.domain.repository.Repository
import com.example.userauthentication.domain.usecase.UserCase_Interfase.UserSignUp
import javax.inject.Inject

class SignUp_UseCase
    @Inject
    constructor(private var repository: Repository)
    :UserSignUp{

    var  readAllData: LiveData<List<User>> =repository.getAllLocalData()

    override suspend fun SignUpUser(user: User) {

        repository.addUser(user)

    }
}