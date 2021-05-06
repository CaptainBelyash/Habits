package com.example.habitsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.habitsapp.adapters.HabitsViewAdapter
import com.example.habitsapp.model.HabitsAppModel
import com.example.habitsapp.model.database.HabitEntity
import kotlinx.android.synthetic.main.fragment_habits_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EditorViewModel(private val model: HabitsAppModel) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

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

    fun addOrReplaceHabit(habit: HabitEntity, oldHabitEntity: HabitEntity? = null) = launch{
        if (oldHabitEntity != null)
            model.delete(oldHabitEntity)
        model.insert(habit)
    }

    fun deleteHabit(habit: HabitEntity) = launch { model.delete(habit) }
}