package com.zhigaras.fitnesskit.data

import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.data.dto.ScheduleDto
import com.zhigaras.fitnesskit.domain.UiText
import com.zhigaras.fitnesskit.domain.entitys.Lesson
import java.time.LocalDate

interface Map<T, R> {
    fun map(input: T): List<R>
    
    class LessonMapper: Map<ScheduleDto, Lesson> {
        
        override fun map(input: ScheduleDto): List<Lesson> {
            return input.lessons.map {lessonDto ->
                Lesson(
                    startTime = lessonDto.startTime,
                    endTime = lessonDto.endTime,
                    place = lessonDto.place,
                    lessonName = lessonDto.name,
                    localDate = LocalDate.parse(lessonDto.date),
                    coachName = input.trainers.find { it.id == lessonDto.coachId }.let {
                        if (it != null) UiText.DynamicString(it.fullName)
                        else UiText.ResourceString(R.string.coach_is_not_yet_appointed)
                    }
                )
            }
        }
    }
}