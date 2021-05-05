package com.example.habitsapp.model.database

import androidx.room.*
import java.util.*

@Entity(tableName = "habits")
data class HabitEntity(@ColumnInfo val name: String?,
                       @ColumnInfo val description: String?,
                       @ColumnInfo val priority: String?,
                       @ColumnInfo val periodicity: String?,
                       @ColumnInfo val type: String?){
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}