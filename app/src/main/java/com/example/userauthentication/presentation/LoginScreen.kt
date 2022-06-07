package com.example.userauthentication.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.userauthentication.databinding.ActivityLoginScreenBinding
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class LoginScreen : AppCompatActivity(),CoroutineScope {

    private lateinit var binding: ActivityLoginScreenBinding


    private lateinit var job:Job

    @Inject
    lateinit var mUserViewModel: UserViewModel

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        job=Job()
        binding.login.setOnClickListener {

            val c:Context=this
            launch {
                if (checkUserCredentials()) {

                        Toast.makeText(c, "User Successfully logined", Toast.LENGTH_SHORT).show()
                }
            }
            displayLocalData()
            displayRemoteData()
        }

        binding.SignUp.setOnClickListener {
            startActivity(Intent(this, SignupScreen::class.java))
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    fun displayRemoteData(){
        System.out.println("--------------------Remote Data----------------------")
        mUserViewModel.myCustomPosts3.observe(this, Observer { response ->
            if(response.isSuccessful){
                for (i in response.body()!!){
                    System.out.println(i)
                }
            }else {
                System.out.println(response.code())
            }
        })
    }

    fun displayLocalData(){
        System.out.println("--------------------Local Data-----------------------")
        mUserViewModel.readAllData
            .observe(this, Observer { user ->
                System.out.println(user)
            })
    }

    suspend fun checkUserCredentials():Boolean{

        if(binding.username.text.toString()==""){
           return false
        }
        else if(binding.password.text.toString()==""){
           return false
        }

        return withContext(Dispatchers.IO){
            mUserViewModel.LoginUser(binding.username.text.toString()
                ,binding.password.text.toString())
        }

    }

}