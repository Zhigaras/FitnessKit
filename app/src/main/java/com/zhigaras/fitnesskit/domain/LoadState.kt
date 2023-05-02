package com.zhigaras.fitnesskit.domain

sealed class LoadState<out T>(
    val data: T?,
    val errorMessage: UiText?
) {
    
    class Success<out T>(_data: T?) : LoadState<T>(
        data = _data,
        errorMessage = null
    )
    
    class Error<out T>(
        val exception: UiText
    ) : LoadState<T>(
        data = null,
        errorMessage = exception
    )
    
    class Loading<out T>(_data: T? = null) : LoadState<T>(
        data = _data,
        errorMessage = null
    )
}