package com.example.memeshare.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://meme-api.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MemeApiService {
    @GET("gimme")
    suspend fun getMeme(): MemePhoto
}
object MemeApi {
    val retrofitService: MemeApiService by lazy {
        retrofit.create(MemeApiService::class.java)
    }
}