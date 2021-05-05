package com.example.habitsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.habitsapp.model.HabitsAppModel
import com.example.habitsapp.model.database.HabitsDB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController by lazy {
            findNavController(R.id.nav_host_fragment)
        }

        nav_view.setupWithNavController(navController)
        if (savedInstanceState == null)
            navController.setGraph(R.navigation.nav_graph)
    }
}