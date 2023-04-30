package com.zhigaras.fitnesskit.data

interface MainRepository {
    
    class Base(
        private val remoteApi: RemoteApi
    ) : MainRepository
}