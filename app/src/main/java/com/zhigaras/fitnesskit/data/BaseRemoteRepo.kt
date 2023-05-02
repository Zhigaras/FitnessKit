package com.zhigaras.fitnesskit.data

import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.domain.LoadState
import com.zhigaras.fitnesskit.domain.UiText
import retrofit2.Response

abstract class BaseRemoteRepo {
    
    suspend fun <T, E> safeApiCall(
        apiToBeCalled: suspend () -> Response<T>,
        mapData: (T) -> List<E>
    ): LoadState<List<E>> {
        return try {
            val response = apiToBeCalled()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null)
                    LoadState.Success(_data = mapData(data))
                else
                    LoadState.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            } else {
                LoadState.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            }
        } catch (e: Exception) {
            LoadState.Error(exception = UiText.ResourceString(R.string.check_connection))
        }
    }
}
