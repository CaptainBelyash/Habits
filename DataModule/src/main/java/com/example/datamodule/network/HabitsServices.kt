package com.example.datamodule.network

import com.example.domainmodule.HabitEntity
import com.google.gson.JsonObject
import retrofit2.http.*

interface HabitsServices {
    @GET("habit")
    suspend fun getHabits(@Header("Authorization") auth: String): List<HabitEntity>

    @PUT("habit")
    suspend fun addOrUpdateHabit(@Header("Authorization") auth: String, @Body habit: HabitEntity)

    @HTTP(method = "DELETE", path = "habit", hasBody = true)
    suspend fun deleteHabit(@Header("Authorization") auth: String, @Body jsonUid: JsonObject)
}