package com.zhigaras.fitnesskit.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrainerDto(
    @Json(name = "description")
    val description: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "image_url_medium")
    val imageUrlMedium: String,
    @Json(name = "image_url_small")
    val imageUrlSmall: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "position")
    val position: String
)