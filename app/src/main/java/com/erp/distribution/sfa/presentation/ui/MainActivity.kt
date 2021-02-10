package com.erp.distribution.sfa.presentation.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

import com.erp.distribution.sfa.R


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navControllerOfNavGraphOfMainActivity: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

//        setSupportActionBar(toolbar)
//        val navController = findNavController(R.id.nav_fragment)
//        NavigationUI.setupWithNavController(toolbar, navController)


        val navHostFragmentOfMainActivity = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navControllerOfNavGraphOfMainActivity = navHostFragmentOfMainActivity.findNavController()

        setupActionBarWithNavController(navControllerOfNavGraphOfMainActivity)


    }
}