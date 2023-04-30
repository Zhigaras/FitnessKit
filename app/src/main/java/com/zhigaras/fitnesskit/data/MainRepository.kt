package com.zhigaras.fitnesskit.data

import android.util.Log
import com.zhigaras.fitnesskit.data.dto.ScheduleDto
import com.zhigaras.fitnesskit.domain.ApiResult

interface MainRepository {
    
    suspend fun fetchSchedule(): ApiResult<ScheduleDto>
    
    class Base(
        private val remoteApi: RemoteApi
    ) : MainRepository, BaseRemoteRepo() {
        
        override suspend fun fetchSchedule(): ApiResult<ScheduleDto> {
            val result = remoteApi.getSchedule()
            Log.d("AAA repo", result.body().toString())
            return safeApiCall { remoteApi.getSchedule() }
        }
        
        
    }
}