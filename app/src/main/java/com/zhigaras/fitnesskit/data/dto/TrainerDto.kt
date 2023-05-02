package com.zhigaras.fitnesskit.data.dto


import com.google.gson.annotations.SerializedName

data class TrainerDto(
    val description: String,
    @SerializedName("full_name")
    val fullName: String,
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("image_url_medium")
    val imageUrlMedium: String,
    @SerializedName("image_url_small")
    val imageUrlSmall: String,
    @SerializedName("last_name")
    val lastName: String,
    val name: String,
    val position: String
)