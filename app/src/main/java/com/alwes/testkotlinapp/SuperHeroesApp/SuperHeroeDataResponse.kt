package com.alwes.testkotlinapp.SuperHeroesApp

import com.google.gson.annotations.SerializedName

data class SuperHeroeDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeroes: List<SuperHeroesItemResponse>
)

data class SuperHeroesItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superHeroeImage: SuperHeroesImageResponse,
    @SerializedName("biography") val superHeroesBiography: SuperHeroesBiographyResponse
)
data class SuperHeroesImageResponse(@SerializedName("url") val url: String)

data class SuperHeroesBiographyResponse(
    @SerializedName("full-name") val realName: String,
    @SerializedName("publisher") val publisher: String
)
