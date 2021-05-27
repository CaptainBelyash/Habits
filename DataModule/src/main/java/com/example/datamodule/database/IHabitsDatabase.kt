package com.example.datamodule.database

interface IHabitsDatabase {
    fun habitsDao(): HabitsDao
}