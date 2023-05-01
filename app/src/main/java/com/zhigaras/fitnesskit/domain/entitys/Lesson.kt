package com.zhigaras.fitnesskit.domain.entitys

import com.zhigaras.fitnesskit.domain.UiText
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

data class Lesson(
    val startTime: String,
    val endTime: String,
    val place: String,
    val coachName: UiText,
    val lessonName: String,
    val localDate: LocalDate,
) {
    val formattedDate =
        localDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM", Locale.getDefault()))
    
}
