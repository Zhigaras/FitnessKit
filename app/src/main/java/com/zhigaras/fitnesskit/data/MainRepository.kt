package com.zhigaras.fitnesskit.data

import com.zhigaras.fitnesskit.data.dto.ScheduleDto
import com.zhigaras.fitnesskit.domain.LoadState
import com.zhigaras.fitnesskit.ui.adapter.LessonListItem

interface MainRepository {
    
    suspend fun fetchSchedule(): LoadState<List<LessonListItem>>
    
    class Base(
        private val remoteApi: RemoteApi,
        private val scheduleToItemListMapper: Map.Base<ScheduleDto, LessonListItem>
    ) : MainRepository, BaseRemoteRepo() {
        
        override suspend fun fetchSchedule(): LoadState<List<LessonListItem>> {
            return safeApiCall(
                apiToBeCalled = { remoteApi.getSchedule() },
                mapData = { scheduleToItemListMapper.map(it) }
            )
        }
    }
}