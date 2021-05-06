package com.example.habitsapp.model.database

import android.content.Context
import androidx.room.*

@Database(entities = [HabitEntity::class], version = 1)
abstract class HabitsDB : RoomDatabase() {
    abstract fun habitsDao(): HabitsDao

    companion object {
        @Volatile
        private var INSTANCE: HabitsDB? = null

        fun getDatabase(context: Context): HabitsDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitsDB::class.java,
                    "HabitsDB"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }
}