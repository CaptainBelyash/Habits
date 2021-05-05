package com.example.habitsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.habitsapp.model.HabitsAppModel
import com.example.habitsapp.model.database.HabitEntity

class HomeViewModel(private val model: HabitsAppModel) : ViewModel() {
    val habits: LiveData<List<HabitEntity>> = model.getHabits()


    fun getHabitsByType(filterRule: String): LiveData<List<HabitEntity>> {
        return model.getHabitsByType(filterRule)
    }

}