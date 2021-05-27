package com.example.domainmodule

import androidx.room.*
import com.example.domainmodule.enums.HabitType
import com.example.domainmodule.enums.Priority
import java.util.*

@Entity(tableName = "habits")
data class HabitEntity(@PrimaryKey var id: String,
                       @ColumnInfo val name: String?,
                       @ColumnInfo val description: String?,
                       @ColumnInfo val priority: Priority,
                       @ColumnInfo val frequency: Int = 0,
                       @ColumnInfo val type: HabitType,
                       @ColumnInfo val color: Int = 0,
                       @ColumnInfo val count: Int = 0,
                       @ColumnInfo val date: Int = 0,
                       @ColumnInfo val doneDates: MutableList<Int> = mutableListOf(),
                       @ColumnInfo var uid: String? = null)