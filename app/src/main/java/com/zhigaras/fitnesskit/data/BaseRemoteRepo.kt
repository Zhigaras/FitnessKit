package com.zhigaras.fitnesskit.data

import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.domain.ApiResult
import com.zhigaras.fitnesskit.domain.UiText
import retrofit2.Response

abstract class BaseRemoteRepo {
    
    suspend fun <T, E> safeApiCall(
        apiToBeCalled: suspend () -> Response<T>,
        mapData: (T) -> List<E>
    ): ApiResult<List<E>> {
        return try {
            val response = apiToBeCalled()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null)
                    ApiResult.Success(_data = mapData(data))
                else
                    ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            } else {
                ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            }
        } catch (e: Exception) {
            ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
        }
    }
}
