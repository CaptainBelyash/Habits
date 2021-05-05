package com.example.habitsapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.habitsapp.fragments.HabitsListFragment

class Pager2Adapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment  = when (position){
        0 -> {
            HabitsListFragment.newInstance("Хорошая")
        }
        else -> {
            HabitsListFragment.newInstance("Плохая")
        }
    }
}