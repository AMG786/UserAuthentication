package com.example.userauthentication.Modules

import android.app.Application
import com.example.userauthentication.data.DataSorce
import com.example.userauthentication.data.UserRepository
import com.example.userauthentication.data.cache.CacheDataSorce
import com.example.userauthentication.data.cache.data.UserDao
import com.example.userauthentication.data.cache.data.UserDatabase
import com.example.userauthentication.data.remote.RemoteDataSorce
import com.example.userauthentication.domain.repository.Repository
import com.example.userauthentication.domain.usecase.UserCase_Interfase.UserLogin
import com.example.userauthentication.domain.usecase.UserCase_Interfase.UserSignUp
import com.example.userauthentication.domain.usecase.UsercasesImp.Login_UseCase
import com.example.userauthentication.domain.usecase.UsercasesImp.SignUp_UseCase
import com.example.userauthentication.presentation.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MyModule {

    //Remote data source
    @RemoteSorce
    @Singleton
    @Provides
    fun provide_dataSorceRemote():DataSorce{
        return RemoteDataSorce()
    }

    //cache data source
    @CacheSorce
    @Singleton
    @Provides
    fun provide_dataSorceCache(userDao: UserDao):DataSorce{
        return CacheDataSorce(userDao)
    }

    // View Model
    @Singleton
    @Provides
    fun provide_UserViewModel(application: Application,userLogin: UserLogin,userSignUp: UserSignUp): UserViewModel {
        return UserViewModel(application,userLogin,userSignUp)
    }

    // view module repository
    @Singleton
    @Provides
    fun provide_UserRepository(@CacheSorce dataSource1: DataSorce, @RemoteSorce dataSource2: DataSorce): Repository {
        return UserRepository(dataSource1, dataSource2)
    }

    // Dao instance
    @Singleton
    @Provides
    fun provide_UserDao(application:Application):UserDao{
        return UserDatabase.getDatabase(
            application
        ).userDao();
    }

    // Login use case Instance
    @Singleton
    @Provides
    fun provide_LoginUseCase(repository: Repository):UserLogin{
        return Login_UseCase(repository)
    }
    // SignUp usecase Instance
    @Singleton
    @Provides
    fun provide_SignupUseCase(repository: Repository):UserSignUp{
        return SignUp_UseCase(repository)
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CacheSorce

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteSorce


