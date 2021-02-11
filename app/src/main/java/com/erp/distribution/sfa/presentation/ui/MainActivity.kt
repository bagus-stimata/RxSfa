package com.erp.distribution.sfa.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.presentation.base.BaseFragment
import com.erp.distribution.sfa.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint


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
//
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


    //Put on BaseActivity
    protected fun onNetworkChange(block: (Boolean) -> Unit) {
        NetworkUtils.getNetworkStatus(this)
                .observe(this, Observer { isConnected ->
                    block(isConnected)
                })
    }

    private fun observeNetworkChanges(characterUrl: String) {
        onNetworkChange { isConnected ->

            characterDetailViewModel.detailViewState.value?.let { viewState ->
                if (isConnected && viewState.error != null) {
                    onErrorResolved()
                    characterDetailViewModel.getCharacterDetails(characterUrl, isRetry = true)
                }
            }



        }
    }


}