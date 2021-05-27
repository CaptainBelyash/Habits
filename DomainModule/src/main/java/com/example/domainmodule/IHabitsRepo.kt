package com.example.domainmodule

import kotlinx.coroutines.flow.Flow

interface IHabitsRepo {
    fun getHabits() : Flow<List<HabitEntity>>

    fun getHabitsByType(type: String): Flow<List<HabitEntity>>

    fun getHabitById(id: String?): Flow<HabitEntity>

    suspend fun insert(habit: HabitEntity)

    suspend fun delete(habit: HabitEntity)

    fun refresh()
}