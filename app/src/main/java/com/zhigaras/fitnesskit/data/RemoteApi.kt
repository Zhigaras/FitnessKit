package com.zhigaras.fitnesskit.data

import com.zhigaras.fitnesskit.data.dto.ScheduleDto
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApi {
    
    companion object {
        const val BASE_URL = "https://olimpia.fitnesskit-admin.ru/"
    }
    
    @GET("schedule/get_v3/?club_id=2")
    suspend fun getSchedule(): Response<ScheduleDto>
}