package com.example.habitsapp.model.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitsDao {
    @Query("SELECT * FROM habits")
    fun getAll(): LiveData<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE type LIKE :type")
    fun getByType(type: String?): LiveData<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE id LIKE :id")
    fun getById(id: Int?): LiveData<HabitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg habit: HabitEntity)

    @Delete
    fun delete(habit: HabitEntity)
}