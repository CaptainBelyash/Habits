package com.example.domainmodule

import kotlinx.coroutines.flow.Flow

class HabitsUseCases(private val repo: IHabitsRepo) {
    suspend fun insert(habit: HabitEntity) = repo.insert(habit)

    suspend fun delete(habit: HabitEntity) = repo.delete(habit)

    fun getHabits() : Flow<List<HabitEntity>> = repo.getHabits()

    fun getHabitsByType(type: String): Flow<List<HabitEntity>> = repo.getHabitsByType(type)

    fun getHabitById(id: String?): Flow<HabitEntity> = repo.getHabitById(id)

    fun refresh() = repo.refresh()
}