package com.example.habitsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitsapp.R
import com.example.habitsapp.adapters.Pager2Adapter
import com.example.habitsapp.MainActivity
import com.example.habitsapp.viewmodels.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val usecases = (requireActivity() as MainActivity).appComponent.getHabitsUseCases()
        homeViewModel = ViewModelProvider(activity!!, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(usecases) as T
            }
        }).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pager.adapter = Pager2Adapter(this)
        TabLayoutMediator(tab_layout, pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Хорошие"
                1 -> "Плохие"
                else -> "Другое"
            }
        }.attach()
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons(){
        create_habit_fab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_editorFragment)
        }
        refresh_button.setOnClickListener{
            homeViewModel.refresh()
        }
    }
}