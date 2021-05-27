package com.example.habitsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    lateinit var appComponent: ApplicationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerApplicationComponent.builder().habitsModule(HabitsModule(this)).build()

        setContentView(R.layout.activity_main)
        val navController by lazy {
            findNavController(R.id.nav_host_fragment)
        }

        nav_view.setupWithNavController(navController)
        if (savedInstanceState == null)
            navController.setGraph(R.navigation.nav_graph)
    }
}