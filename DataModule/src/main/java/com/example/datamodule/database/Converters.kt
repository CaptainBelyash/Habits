package com.example.datamodule.database

import androidx.room.TypeConverter
import com.example.domainmodule.enums.HabitType
import com.example.domainmodule.enums.Priority
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun toPriority(value: Int) = enumValues<Priority>()[value]

    @TypeConverter
    fun fromPriority(value: Priority) = value.ordinal

    @TypeConverter
    fun toHabitType(value: Int) = enumValues<HabitType>()[value]

    @TypeConverter
    fun fromHabitType(value: HabitType) = value.ordinal

    @TypeConverter
    fun toIntList(value: String) : List<Int>{
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromIntList(value: List<Int>) = Gson().toJson(value)
}