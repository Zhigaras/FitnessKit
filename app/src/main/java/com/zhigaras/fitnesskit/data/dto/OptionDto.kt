package com.zhigaras.fitnesskit.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OptionDto(
    @Json(name = "club_name")
    val clubName: String,
    @Json(name = "link_android")
    val linkAndroid: String,
    @Json(name = "link_ios")
    val linkIos: String,
    @Json(name = "primary_color")
    val primaryColor: String,
    @Json(name = "secondary_color")
    val secondaryColor: String
)