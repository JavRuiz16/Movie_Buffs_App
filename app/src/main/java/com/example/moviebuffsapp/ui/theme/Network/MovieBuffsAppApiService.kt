package com.example.moviebuffsapp.ui.theme.Network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


private const val BASE_URL =
    "https://kareemy.github.io"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()
interface MovieBuffsAppAprService {
    @GET("MovieBuffsApp/photos.json")
    suspend fun getPhotos(): List<MovieBuffsAppPhoto>
}

object MarsApi {
    val retrofitService : MovieBuffsAppAprService by lazy {
        retrofit.create(MovieBuffsAppAprService::class.java)
    }
}