package com.zhigaras.fitnesskit.ui

import androidx.lifecycle.ViewModel
import com.zhigaras.fitnesskit.data.core.DispatchersModule
import com.zhigaras.fitnesskit.data.MainRepository

class ScheduleViewModel(
    private val mainRepository: MainRepository,
    private val dispatchers: DispatchersModule
) : ViewModel() {

}