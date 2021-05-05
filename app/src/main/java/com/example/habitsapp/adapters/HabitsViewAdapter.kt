package com.example.habitsapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitsapp.R
import com.example.habitsapp.fragments.HabitsListFragment
import com.example.habitsapp.model.database.HabitEntity
import java.util.ArrayList


class HabitsViewAdapter(private val habits: List<HabitEntity>,
                        private val rv: RecyclerView,
                        private val habitsListFragment: HabitsListFragment
)
    : RecyclerView.Adapter<HabitsViewAdapter.ViewHolder>(), View.OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.habits_list_element_layout, parent, false)
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position].name!!)
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    class ViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        private val element: TextView = itemView.findViewById(R.id.element)

        fun bind(data: String){
            element.text = data
        }
    }

    override fun onClick(v: View?) {
        habitsListFragment.handleRVClick(v?.let { habits[rv.getChildLayoutPosition(it)].id }!!) }
}