package com.zhigaras.fitnesskit.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhigaras.fitnesskit.data.MainRepository
import com.zhigaras.fitnesskit.data.core.DispatchersModule
import com.zhigaras.fitnesskit.domain.ApiResult
import com.zhigaras.fitnesskit.ui.adapter.LessonListItem
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val mainRepository: MainRepository,
    private val dispatchers: DispatchersModule,
    private val communication: Communication.Mutable<ApiResult<List<LessonListItem>>>,
) : ViewModel(), Communication.Observe<ApiResult<List<LessonListItem>>>, ScheduleInteract {
    
    init {
        fetchSchedule()
    }
    
    override fun observe(owner: LifecycleOwner, observer: Observer<ApiResult<List<LessonListItem>>>) {
        communication.observe(owner, observer)
    }
    
    override fun fetchSchedule() {
        viewModelScope.launch {
            mainRepository.fetchSchedule().let { communication.map(it) }
        }
    }
}

interface ScheduleInteract {
    
    fun fetchSchedule()
    
}