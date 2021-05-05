package com.example.habitsapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.habitsapp.model.database.HabitEntity
import com.example.habitsapp.model.database.HabitsDao

object HabitsAppModel{
    private lateinit var habitsDao : HabitsDao

    fun withDao(dao: HabitsDao): HabitsAppModel{
        habitsDao = dao
        return this
    }

    fun getHabits(): LiveData<List<HabitEntity>>{
        return habitsDao.getAll()
    }

    fun getHabitsByType(type: String?): LiveData<List<HabitEntity>>{
        return habitsDao.getByType(type)
    }

    fun getHabitById(id: Int?): LiveData<HabitEntity>{
        return habitsDao.getById(id)
    }

    fun insert(habit: HabitEntity){
        habitsDao.insertAll(habit)
    }

    fun delete(habit: HabitEntity){
        habitsDao.delete(habit)
    }
}