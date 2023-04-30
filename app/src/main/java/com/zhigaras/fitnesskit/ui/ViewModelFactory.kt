package com.zhigaras.fitnesskit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhigaras.fitnesskit.data.core.Core

class ViewModelFactory(
    private val core: Core
) : ViewModelProvider.Factory {
    
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val mainRepository = core.provideRepository()
        val dispatchers = core.provideDispatchers()
        return when (modelClass) {
            ScheduleViewModel::class.java -> ScheduleViewModel(
                mainRepository,
                dispatchers,
                Communication.Base()
            ) as T
            
            else -> throw IllegalArgumentException("Unknown class name: $modelClass")
        }
    }
}