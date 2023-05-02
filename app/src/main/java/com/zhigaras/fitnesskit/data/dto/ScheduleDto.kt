package com.zhigaras.fitnesskit.data.dto


data class ScheduleDto(
    val lessons: List<LessonDto>,
    val option: OptionDto,
    val tabs: List<TabDto>,
    val trainers: List<TrainerDto>
)