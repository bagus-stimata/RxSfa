package com.erp.distribution.sfa.presentation.ui.material

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.erp.distribution.sfa.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaterialActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_material) as NavHostFragment
        navController = navHostFragment.findNavController()
        //Ingat pada nav_host_fragment -> nav_graph_material sudah disetting Fragment utama yang akan muncul pertama kali

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

const val ADD_TASK_RESULT_OK = Activity.RESULT_FIRST_USER
const val EDIT_TASK_RESULT_OK = Activity.RESULT_FIRST_USER + 1