package ru.cft.shift2021summer.feature.beers.domain

import com.google.gson.annotations.SerializedName

data class BeerModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("first_brewed")
    val first_brewed: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("brewers_tips")
    val brewers_tips: String,
    @SerializedName("abv")
    val abv: Double
)