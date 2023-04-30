package com.zhigaras.fitnesskit.data.dto


import com.google.gson.annotations.SerializedName

data class ScheduleDto(
    @SerializedName("lessons")
    val lessons: List<LessonDto>,
    @SerializedName("option")
    val option: OptionDto,
    @SerializedName("tabs")
    val tabs: List<TabDto>,
    @SerializedName("trainers")
    val trainers: List<TrainerDto>
)