package com.zhigaras.fitnesskit.data.dto


import com.google.gson.annotations.SerializedName

data class TrainerDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("image_url_medium")
    val imageUrlMedium: String,
    @SerializedName("image_url_small")
    val imageUrlSmall: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: String
)