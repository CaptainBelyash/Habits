package com.example.habitsapp

import com.example.domainmodule.HabitsUseCases
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HabitsModule::class])
interface ApplicationComponent {
    fun getHabitsUseCases(): HabitsUseCases
}