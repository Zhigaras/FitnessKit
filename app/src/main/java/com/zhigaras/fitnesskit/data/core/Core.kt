package com.zhigaras.fitnesskit.data.core

import com.zhigaras.fitnesskit.data.RemoteApi
import com.zhigaras.fitnesskit.data.MainRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface Core {
    
    fun provideDispatchers(): DispatchersModule
    
    fun provideRepository(): MainRepository
    
    class Base : Core {
        
        private val dispatchersModule by lazy {
            DispatchersModule.Base()
        }
        
        private val remoteApi = Retrofit.Builder()
            .baseUrl(RemoteApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
        
        override fun provideDispatchers(): DispatchersModule = dispatchersModule
        
        override fun provideRepository(): MainRepository = MainRepository.Base(remoteApi)
        
    }
}