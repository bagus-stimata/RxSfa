package com.erp.distribution.sfa.presentation.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.MainActivityBinding
import com.erp.distribution.sfa.presentation.base.BaseActivity
import com.erp.distribution.sfa.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.textColor


@AndroidEntryPoint
internal class MainActivity : BaseActivity() {

    lateinit var binding: MainActivityBinding
    private lateinit var navControllerOfNavGraphOfMainActivity: NavController

    val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(toolbar)
//        val navController = findNavController(R.id.nav_fragment)
//        NavigationUI.setupWithNavController(toolbar, navController)

        val navHostFragmentOfMainActivity = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navControllerOfNavGraphOfMainActivity = navHostFragmentOfMainActivity.findNavController()
        setupActionBarWithNavController(navControllerOfNavGraphOfMainActivity)

        /**
         * Cek Network
         */
        observeNetworkChange()

    }



    /**
     * Teknik on Back Navigation akan berlaku
     */
//    override fun onSupportNavigateUp(): Boolean {
//        Toast.makeText(this, "Ngene iki " + navControllerOfNavGraphOfMainActivity.currentDestination .toString(), Toast.LENGTH_LONG).show()
//
//        return navControllerOfNavGraphOfMainActivity.navigateUp() || super.onSupportNavigateUp()
//    }

//    override fun onBackPressed() {
//        Toast.makeText(this, "Back Press Level Activity", Toast.LENGTH_LONG).show()
//        super.onBackPressed()
//    }
//    override fun onBackPressed() {
//        val fragmentList: List<*> = supportFragmentManager.fragments
//        var handled = false
//        for (f in fragmentList) {
//            if (f is BaseFragment<*, *>) {
//                handled = f.onBackPressed()
//                if (handled) {
//                    break
//                }
//            }
//        }
//        if (!handled) {
//            super.onBackPressed()
//        }
//    }


    fun observeNetworkChange() {
        onNetworkChange { isConnected ->
            if (isConnected) {
                binding.mainErrorTextView.visibility = View.GONE
            } else if (!isConnected) {
//                Toast.makeText(this, "Terputus ${isConnected}", Toast.LENGTH_LONG).show()
                binding.mainErrorTextView.textColor = Color.RED
                binding.mainErrorTextView.text = "Tidak ada koneksi internet"
                binding.mainErrorTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun onErrorResolved() {
//        binding.mainErrorTextView.visibility = View.GONE
//        binding.filmsLayout.filmsErrorTextView.remove()
//        binding.planetLayout.planetErrorTextView.remove()
//        binding.specieLayout.specieErrorTextView.remove()
//        binding.filmsLayout.filmsProgressBar.show()
//        binding.planetLayout.planetProgressBar.show()
//        binding.specieLayout.speciesProgressBar.show()
    }



}