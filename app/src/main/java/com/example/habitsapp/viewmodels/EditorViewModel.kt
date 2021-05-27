package com.example.habitsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domainmodule.HabitsUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.asLiveData

class EditorViewModel(private val useCases: HabitsUseCases)
    : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    fun getHabitFromFields(fieldsData: Map<String, String?>, id: String? = null, uid: String? = null): com.example.domainmodule.HabitEntity {
        val frequency = fieldsData["periodicity"] ?: "0"
        val habit = com.example.domainmodule.HabitEntity(
            UUID.randomUUID().toString(),
            fieldsData["name"] ?: "Без имени",
            fieldsData["description"] ?: "Без описания",
            com.example.domainmodule.enums.Priority.valueOf(fieldsData["priority"]!!),
            frequency.toInt(),
            com.example.domainmodule.enums.HabitType.valueOf(fieldsData["type"]!!)
        )
        if (id != null)
            habit.id = id
        if (uid != null)
            habit.uid = uid
        return habit
    }

    fun GetHabitById(id: String): LiveData<com.example.domainmodule.HabitEntity>{
        return useCases.getHabitById(id).asLiveData()
    }

    fun insertHabit(habit: com.example.domainmodule.HabitEntity) = launch{ useCases.insert(habit) }

    fun deleteHabit(habit: com.example.domainmodule.HabitEntity) = launch { useCases.delete(habit) }
}