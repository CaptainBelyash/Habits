package com.example.datamodule.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.domainmodule.HabitEntity
import com.example.domainmodule.enums.HabitType
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitsDao {
    @Query("SELECT * FROM habits")
    fun getAll(): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits")
    fun getAllFlat(): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE type LIKE :type")
    fun getByType(type: HabitType?): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE id LIKE :id")
    fun getById(id: String?): Flow<HabitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg habit: HabitEntity)

    @Delete
    fun delete(habit: HabitEntity)
}