package com.zhigaras.fitnesskit.domain.entitys

import com.zhigaras.fitnesskit.R
import com.zhigaras.fitnesskit.domain.UiText
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class Lesson(
    val startTime: String,
    val endTime: String,
    val place: String,
    val coachName: UiText,
    val lessonName: String,
    val localDate: LocalDate,
    val markerColor: Int
) {
    val formattedDate: String =
        localDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM", Locale.getDefault()))
    
    fun duration(): UiText {
        val duration = Duration.between(LocalTime.parse(startTime), LocalTime.parse(endTime))
        return if (duration.toHours().toInt() == 0) UiText.ResourceString(
            R.string.duration_zero_hours,
            duration.toMinutesPart()
        )
        else UiText.ResourceString(R.string.duration, duration.toHours(), duration.toMinutesPart())
    }
}
