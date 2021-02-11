package com.erp.distribution.sfa.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.MainActivityBinding
import com.erp.distribution.sfa.presentation.base.BaseActivity
import com.erp.distribution.sfa.presentation.base.BaseFragment
import com.erp.distribution.sfa.utils.Constants
import com.erp.distribution.sfa.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class MainActivity : BaseActivity() {

    lateinit var binding: MainActivityBinding
    private lateinit var navControllerOfNavGraphOfMainActivity: NavController

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
        observeNetworkChanges(Constants.BASE_URL)

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


    private fun observeNetworkChanges(characterUrl: String) {
        onNetworkChange { isConnected ->
            if (isConnected){
                Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show()
                binding.mainErrorTextView.visibility = View.GONE
            }else if (!isConnected){
                Toast.makeText(this, "Terputus", Toast.LENGTH_LONG).show()
                binding.mainErrorTextView.text = "Tidak ada koneksi internet"
                binding.mainErrorTextView.visibility = View.VISIBLE
            }
//            characterDetailViewModel.detailViewState.value?.let { viewState ->
//                if (isConnected && viewState.error != null) {
//                    onErrorResolved()
//                    characterDetailViewModel.getCharacterDetails(characterUrl, isRetry = true)
//                }
//            }

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