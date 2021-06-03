package com.example.habitsapp.Screens

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.habitsapp.R


class MainScreen: Screen<MainScreen>() {
    val createHabitButton = KButton{
        withId(R.id.create_habit_fab)
    }
}

