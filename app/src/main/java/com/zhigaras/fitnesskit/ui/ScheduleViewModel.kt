package com.zhigaras.fitnesskit.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhigaras.fitnesskit.data.MainRepository
import com.zhigaras.fitnesskit.data.core.DispatchersModule
import com.zhigaras.fitnesskit.domain.LoadState
import com.zhigaras.fitnesskit.ui.adapter.LessonListItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel(
    private val mainRepository: MainRepository,
    private val dispatchers: DispatchersModule,
    private val communication: Communication.Mutable<LoadState<List<LessonListItem>>>,
) : ViewModel(), Communication.Observe<LoadState<List<LessonListItem>>>, ScheduleInteract {
    
    private var scrollState = 0
    
    init {
        fetchSchedule()
    }
    
    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<LoadState<List<LessonListItem>>>
    ) {
        communication.observe(owner, observer)
    }
    
    override fun fetchSchedule() {
        viewModelScope.launch {
            communication.map(LoadState.Loading())
            val result: LoadState<List<LessonListItem>>
            withContext(dispatchers.io()) {
                result = mainRepository.fetchSchedule()
            }
            communication.map(result)
        }
    }
    
    override fun saveScrollState(state: Int) {
        scrollState = state
    }
    
    override fun restoreScrollState(): Int = scrollState
}

interface ScheduleInteract {
    
    fun fetchSchedule()
    
    fun saveScrollState(state: Int)
    
    fun restoreScrollState(): Int
    
}