package com.example.datamodule

import androidx.lifecycle.LiveData
import com.example.datamodule.database.HabitsDao
import com.example.datamodule.network.HabitsRetrofitClient
import com.example.datamodule.network.HabitsServices
import com.example.domainmodule.HabitEntity
import com.example.domainmodule.IHabitsRepo
import com.example.domainmodule.enums.HabitType
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext

class Repository(var habitsDao: HabitsDao, val retrofit: Retrofit) : CoroutineScope, IHabitsRepo {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private val AUTH_TOKEN = "55baa2bf-2265-4f4a-8026-9f2d674d6306"

    private val service: HabitsServices
        get() = retrofit
            .create(HabitsServices::class.java)

    override fun getHabits(): Flow<List<HabitEntity>> {
        return habitsDao.getAll()
    }

    override fun getHabitsByType(type: String): Flow<List<HabitEntity>> {
        return habitsDao.getByType(HabitType.valueOf(type))
    }

    override fun getHabitById(id: String?): Flow<HabitEntity> {
        return habitsDao.getById(id)
    }

    override suspend fun insert(habit: HabitEntity){
        habitsDao.insertAll(habit)
        service.addOrUpdateHabit(AUTH_TOKEN, habit)
    }

    override suspend fun delete(habit: HabitEntity){
        deleteFromLocal(habit)
        if (habit.uid != null)
            deleteFromNet(habit.uid!!)
    }

    private fun deleteFromLocal(habit: HabitEntity){
        habitsDao.delete(habit)
    }

    private fun deleteFromNet(uid: String) = launch{
        service.deleteHabit(AUTH_TOKEN,
            JsonObject().apply { addProperty("uid", uid) })
    }

    override fun refresh() {
        refreshFromNet()
    }

    private fun refreshFromNet() = launch{
        val habits = service.getHabits(AUTH_TOKEN)
        clearDataBase()
        for (habit in habits)
            habitsDao.insertAll(habit)
    }

    private suspend fun clearDataBase(){
        for (habit in habitsDao.getAllFlat())
            deleteFromLocal(habit)
    }
}