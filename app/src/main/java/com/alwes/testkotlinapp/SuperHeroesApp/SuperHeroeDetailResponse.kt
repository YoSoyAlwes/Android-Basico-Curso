package com.alwes.testkotlinapp.SuperHeroesApp

import com.google.gson.annotations.SerializedName

data class SuperHeroeDetailResponse(
    @SerializedName("response")val response: String,
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerStatsResponse: SuperHeroePowerStatsResponse,
    @SerializedName("biography") val biographyResponse: SuperHeroeBiographyResponse,
    @SerializedName("image") val imageSHD: SuperHeroeImageDetailResponse
)
data class SuperHeroePowerStatsResponse(
    @SerializedName("intelligence")val intelligenceSH: String,
    @SerializedName("strength")val strengthSH:String,
    @SerializedName("speed")val speedSH:String,
    @SerializedName("durability")val durabilitySH:String,
    @SerializedName("power")val powerSH:String,
    @SerializedName("combat")val combatSH:String
)

data class SuperHeroeBiographyResponse(
    @SerializedName("full-name") val fullNameSH:String,
    @SerializedName("alter-egos") val alterEgosSH:String,
    //@SerializedName("aliases") val aliasesSH: List<SuperHeroesAliasesResponse>,
    @SerializedName("place-of-birth") val POBSH:String,
    @SerializedName("first-appearance") val FASH:String,
    @SerializedName("publisher") val publisherSH:String,
    @SerializedName("alignment") val alignmentSH:String,
)
/*
data class SuperHeroesAliasesResponse(
    @SerializedName("aliases") val aliasSH: String
)
data class SuperHeroeAppareanceResponse(
    @SerializedName("gender") val genderSH:String,
    @SerializedName("race") val raceSH:String,
    @SerializedName("height") val  :,
    @SerializedName("weight") val  :String,
    @SerializedName("eye-color") val  :String,
    @SerializedName("hair-color") val  :String
)
*/
data class SuperHeroeImageDetailResponse(@SerializedName("url") val url: String)
