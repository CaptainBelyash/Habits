package com.example.habitsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domainmodule.HabitEntity
import com.example.domainmodule.HabitsUseCases

class HomeViewModel(private val useCases: HabitsUseCases) : ViewModel() {
    val habits: LiveData<List<HabitEntity>> = useCases.getHabits().asLiveData()

    fun getHabitsByType(filterRule: String): LiveData<List<HabitEntity>> {
        return useCases.getHabitsByType(filterRule).asLiveData()
    }

    fun refresh(){
        useCases.refresh()
    }
}