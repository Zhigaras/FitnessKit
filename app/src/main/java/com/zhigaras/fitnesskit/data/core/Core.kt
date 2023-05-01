package com.zhigaras.fitnesskit.data.core

import com.zhigaras.fitnesskit.data.MainRepository
import com.zhigaras.fitnesskit.data.Map
import com.zhigaras.fitnesskit.data.RemoteApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface Core {
    
    fun provideDispatchers(): DispatchersModule
    
    fun provideRepository(): MainRepository
    
    class Base : Core {
        
        private val dispatchersModule by lazy {
            DispatchersModule.Base()
        }
        
        private val remoteApi = Retrofit.Builder()
            .baseUrl(RemoteApi.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().also {
                        it.level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteApi::class.java)
        
        override fun provideDispatchers(): DispatchersModule = dispatchersModule
        
        override fun provideRepository(): MainRepository =
            MainRepository.Base(
                remoteApi,
                Map.ScheduleToItemListMapper(Map.LessonMapper(), Map.ItemListMapper())
            )
        
    }
}