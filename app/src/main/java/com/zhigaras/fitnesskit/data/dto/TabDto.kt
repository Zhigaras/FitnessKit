package com.zhigaras.fitnesskit.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TabDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)