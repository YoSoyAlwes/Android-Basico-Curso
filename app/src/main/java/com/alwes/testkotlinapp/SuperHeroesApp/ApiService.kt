package com.alwes.testkotlinapp.SuperHeroesApp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api.php/api-key/search/{name}")
    suspend fun getSuperHeroe(@Path("name")name: String): Response<SuperHeroeDataResponse>

    @GET("/api.php/api-key/{id}")
    suspend fun getSuperHeroeById(@Path("id")id: String): Response<SuperHeroeDetailResponse>
}