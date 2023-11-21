package com.example.moviebuffsapp.ui.theme.Network

import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://kareemy.github.io/MovieBuffs/movies.json"
interface MovieBuffApiService {

    private val retrofit = Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    object MovieBuffsAppApi {
        val retrofitService : MovieBuffsApiService by lazy {
            retrofit.create(MovieBuffsApiService::class.java)
        }
    }

    interface MovieBuffsApiService {
        @GET("MovieBuffs/json")
        fun getPhotos():String

    }
}