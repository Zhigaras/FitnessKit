package com.zhigaras.fitnesskit.data

import com.zhigaras.fitnesskit.domain.ApiResult
import com.zhigaras.fitnesskit.domain.entitys.Lesson

interface MainRepository {
    
    suspend fun fetchSchedule(): ApiResult<List<Lesson>>
    
    class Base(
        private val remoteApi: RemoteApi,
        mapper: Map.LessonMapper
    ) : MainRepository, BaseRemoteRepo(mapper) {
        
        override suspend fun fetchSchedule(): ApiResult<List<Lesson>> {
            return safeApiCall { remoteApi.getSchedule() }
        }
        
        
    }
}