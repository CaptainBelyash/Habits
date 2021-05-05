package com.example.habitsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitsapp.R
import com.example.habitsapp.adapters.HabitsViewAdapter
import com.example.habitsapp.model.HabitsAppModel
import com.example.habitsapp.model.database.HabitsDB
import com.example.habitsapp.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_habits_list.*
import java.util.*

class HabitsListFragment : Fragment() {
    private lateinit var habitsType: String
    private lateinit var viewModel: HomeViewModel

    companion object {
        @JvmStatic
        fun newInstance(habitsType: String): HabitsListFragment {
            val fragment = HabitsListFragment()
            val bundle = Bundle()
            bundle.putString("habitsType", habitsType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(activity!!, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(HabitsAppModel.withDao(HabitsDB.getDatabase(requireContext()).habitsDao())) as T
            }
        }).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habits_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            habitsType = it.getString("habitsType")!!
        }
        viewModel.getHabitsByType(habitsType).observe(viewLifecycleOwner) {
            recycler_view.adapter = HabitsViewAdapter(
                it.orEmpty(),
                recycler_view, this
            )
        }

    }

    fun handleRVClick(habitId: Int){
        val bundle = Bundle().apply {
            putInt("habitId", habitId)
        }
        findNavController().navigate(R.id.action_homeFragment_to_editorFragment, bundle)
    }
}