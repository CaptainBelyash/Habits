package com.example.habitsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.habitsapp.adapters.HabitsViewAdapter
import com.example.habitsapp.model.HabitsAppModel
import com.example.habitsapp.model.database.HabitEntity
import kotlinx.android.synthetic.main.fragment_habits_list.*

class EditorViewModel(private val model: HabitsAppModel) : ViewModel() {

    fun getHabitFromFields(fieldsData: Map<String, String?>): HabitEntity{
        return HabitEntity(fieldsData["name"],
            fieldsData["description"],
            fieldsData["priority"],
            fieldsData["periodicity"],
            fieldsData["type"])
    }

    fun GetHabitById(id: Int): LiveData<HabitEntity>{
        return model.getHabitById(id)
    }

    fun addOrReplaceHabit(habit: HabitEntity, oldHabitEntity: HabitEntity? = null){
        if (oldHabitEntity != null)
            model.delete(oldHabitEntity)
        model.insert(habit)
    }

    fun deleteHabit(habit: HabitEntity) = model.delete(habit)
}