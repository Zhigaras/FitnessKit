package com.zhigaras.fitnesskit.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScheduleDto(
    @Json(name = "lessons")
    val lessons: List<LessonDto>,
    @Json(name = "option")
    val option: OptionDto,
    @Json(name = "tabs")
    val tabs: List<TabDto>,
    @Json(name = "trainers")
    val trainers: List<TrainerDto>
)