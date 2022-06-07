package com.example.userauthentication.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.userauthentication.databinding.ActivitySignupScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SignupScreen : AppCompatActivity() , CoroutineScope {

    private lateinit var binding: ActivitySignupScreenBinding

    private lateinit var job: Job

    @Inject
    lateinit var mUserViewModel: UserViewModel

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job=Job()

            binding.SignUp.setOnClickListener {

                val c: Context =this
                launch {
                    if (checkUserCredentials()) {
                        mUserViewModel.SignupUser(binding.username.text.toString(),
                            binding.password.text.toString())
                        Toast.makeText(c, "Successfully Account is created", Toast.LENGTH_SHORT).show()
                    }
                }

            }


            binding.login.setOnClickListener { finish() }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    suspend fun checkUserCredentials():Boolean{
        if(binding.username.text.toString()==""){
            return false
        }
        else if(binding.password.text.toString()==""){
            return false
        }
        return !withContext(Dispatchers.IO){
            mUserViewModel.LoginUser(binding.username.text.toString()
                ,binding.password.text.toString())
        }

    }


}