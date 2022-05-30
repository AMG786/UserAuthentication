package com.example.userauthentication.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.retrofit.model.user
import com.example.userauthentication.data.cache.data.User
import com.example.userauthentication.domain.usecase.UserCase_Interfase.UserLogin
import com.example.userauthentication.domain.usecase.UserCase_Interfase.UserSignUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserViewModel
    @Inject
    constructor(application:Application,private val login: UserLogin,private val signUp: UserSignUp)
    :AndroidViewModel(application) {

    val  readAllData: LiveData<List<User>> = login.getAlluser()
    var myCustomPosts3: MutableLiveData<Response<List<user>>> = MutableLiveData()


    init {
         viewModelScope.launch {
             myCustomPosts3.value = login.getAllRemoteData()
         }
     }

    fun SignupUser(username: String,password: String){
        val user=User(0,username,password)
        viewModelScope.launch(Dispatchers.IO) {
            signUp.SignUpUser(user)
        }
    }

    suspend fun LoginUser(username:String, password:String):Boolean{

        val user=User(0,username,password)
        return withContext(Dispatchers.IO){
          login.LoginUser(user)
        }
    }
}

