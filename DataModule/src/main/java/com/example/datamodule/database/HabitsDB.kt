package com.example.datamodule.database

import android.content.Context
import androidx.room.*
import com.example.domainmodule.HabitEntity

@Database(entities = [HabitEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class HabitsDB : RoomDatabase(), IHabitsDatabase {
    abstract override fun habitsDao(): HabitsDao

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