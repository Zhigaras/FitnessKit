package com.zhigaras.fitnesskit.data

import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.data.dto.ScheduleDto
import com.zhigaras.fitnesskit.domain.ApiResult
import com.zhigaras.fitnesskit.domain.UiText
import com.zhigaras.fitnesskit.domain.entitys.Lesson
import retrofit2.Response

abstract class BaseRemoteRepo(
    private val mapper: Map.LessonMapper
) {
    
    suspend fun safeApiCall(apiToBeCalled: suspend () -> Response<ScheduleDto>): ApiResult<List<Lesson>> {
        return try {
            val response = apiToBeCalled()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null)
                    ApiResult.Success(_data = mapper.map(data))
                else
                    ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            } else {
                ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
            }
        } catch (e: Exception) {
            ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
        }
    }
}
