package com.zhigaras.fitnesskit.data.dto


import com.google.gson.annotations.SerializedName

data class TabDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)