package com.zhigaras.fitnesskit.data

import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.data.dto.ScheduleDto
import com.zhigaras.fitnesskit.domain.UiText
import com.zhigaras.fitnesskit.domain.entitys.Lesson
import com.zhigaras.fitnesskit.ui.adapter.ItemType
import com.zhigaras.fitnesskit.ui.adapter.LessonListItem
import java.time.LocalDate

interface Map {
    
    interface Base<T, R> {
        fun map(input: T): List<R>
        
    }
    
    interface ListMap<T, R> {
        
        fun map(input: List<T>): List<R>
        
    }
    
    class LessonMapper : Base<ScheduleDto, Lesson> {
        
        override fun map(input: ScheduleDto): List<Lesson> {
            return input.lessons.map { lessonDto ->
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
    
    class ItemListMapper : ListMap<Lesson, LessonListItem> {
        
        override fun map(input: List<Lesson>): List<LessonListItem> {
            val list = input.sortedByDescending { it.localDate }
            val map = emptyMap<String, ArrayList<Lesson>>().toMutableMap()
            list.forEach {
                if (map[it.formattedDate] == null)
                    map[it.formattedDate] = ArrayList()
                map[it.formattedDate]?.add(it)
            }
            val newLessons = ArrayList<LessonListItem>()
            map.forEach { entry ->
                newLessons.add(LessonListItem(itemType = ItemType.HEADER, header = entry.key))
                entry.value.mapTo(newLessons) {
                    LessonListItem(itemType = ItemType.LESSON, lesson = it)
                }
            }
            return newLessons
        }
    }
    
    class ScheduleToItemListMapper(
        private val lessonMapper: Base<ScheduleDto, Lesson>,
        private val itemListMapper: ListMap<Lesson, LessonListItem>
    ) : Base<ScheduleDto, LessonListItem> {
        
        override fun map(input: ScheduleDto): List<LessonListItem> {
            return itemListMapper.map(lessonMapper.map(input))
        }
    }
}