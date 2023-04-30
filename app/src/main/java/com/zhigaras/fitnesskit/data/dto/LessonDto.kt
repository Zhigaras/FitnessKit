package com.zhigaras.fitnesskit.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LessonDto(
    @Json(name = "appointment_id")
    val appointmentId: String,
    @Json(name = "available_slots")
    val availableSlots: Int,
    @Json(name = "client_recorded")
    val clientRecorded: Boolean,
    @Json(name = "coach_id")
    val coachId: String,
    @Json(name = "color")
    val color: String,
    @Json(name = "commercial")
    val commercial: Boolean,
    @Json(name = "date")
    val date: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "endTime")
    val endTime: String,
    @Json(name = "is_cancelled")
    val isCancelled: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "place")
    val place: String,
    @Json(name = "service_id")
    val serviceId: String,
    @Json(name = "startTime")
    val startTime: String,
    @Json(name = "tab")
    val tab: String,
    @Json(name = "tab_id")
    val tabId: Int
)