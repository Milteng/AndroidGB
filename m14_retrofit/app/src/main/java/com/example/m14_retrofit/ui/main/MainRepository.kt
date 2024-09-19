package com.example.m14_retrofit.ui.main

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://randomuser.me"

class MainRepository {
    private val moshi =
        Moshi.Builder().addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()



    val searchRandomUser: SearchRandomUser = retrofit.create(SearchRandomUser::class.java)
    val getRandomUser: GetRandomUser = retrofit.create(GetRandomUser::class.java)

}

interface SearchRandomUser {
    @GET("/{api}/")
    suspend fun getRandomUser(@Path("api") api:String = "api", @Query("inc") inc:String = "name,picture"): Response<Results>
}

interface GetRandomUser {
    @GET("/api/?inc=name,picture")
    suspend fun getRandomUser(): Call<List<Results>>
}