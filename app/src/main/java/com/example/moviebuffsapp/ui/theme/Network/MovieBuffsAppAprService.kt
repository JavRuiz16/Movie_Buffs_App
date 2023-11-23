package com.example.moviebuffsapp.ui.theme.Network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://kareemy.github.io"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MovieBuffsAppAprService {
    @GET("Mars/photos.json")
    fun getPhotos(): String

}

object MarsApi {
    val retrofitService : MovieBuffsAppAprService by lazy {
        retrofit.create(MovieBuffsAppAprService::class.java)
    }
}