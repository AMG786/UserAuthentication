package com.example.userauthentication.domain.usecase.UserCase_Interfase

import com.example.userauthentication.data.cache.data.User

interface UserSignUp {
    suspend fun SignUpUser(user: User)
}