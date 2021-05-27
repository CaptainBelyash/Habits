package com.example.datamodule.network

import com.example.datamodule.network.JSON.HabitJsonDeserializer
import com.example.datamodule.network.JSON.HabitJsonSerializer
import com.example.domainmodule.HabitEntity
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HabitsRetrofitClient {
    fun getClient(baseUrl: String): Retrofit {
        val habitsGson = GsonBuilder()
            .registerTypeAdapter(HabitEntity::class.java, HabitJsonSerializer())
            .registerTypeAdapter(HabitEntity::class.java, HabitJsonDeserializer())
            .create()

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(habitsGson))
            .build()
    }
}