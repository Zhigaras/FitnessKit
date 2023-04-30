package com.zhigaras.fitnesskit.data

import android.util.Log
import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.domain.ApiResult
import com.zhigaras.fitnesskit.domain.UiText
import retrofit2.Response

abstract class BaseRemoteRepo {
    
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): ApiResult<T> {
        return try {
            val response = apiToBeCalled()
            Log.d("AAA", response.body().toString())
            if (response.isSuccessful) {
                ApiResult.Success(_data = response.body())
            } else {
                ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
            }
        } catch (e: Exception) {
            ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
        }
    }
}
