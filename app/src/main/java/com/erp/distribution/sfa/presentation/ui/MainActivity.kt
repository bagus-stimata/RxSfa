package com.erp.distribution.sfa.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.MainActivityBinding
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.presentation.base.BaseActivity
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.ui.customer.customer_list.CustomerFragmentDirections
import com.erp.distribution.sfa.presentation.ui.utils.AlertDialogConfirm
import com.erp.distribution.sfa.utils.network.NetworkChecker
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.*


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    val TAG = MainActivity::class.java.simpleName

    lateinit var binding: MainActivityBinding
    private lateinit var navControllerOfNavGraphOfMainActivity: NavController

    val mainViewModel: MainViewModel by viewModels<MainViewModel>()

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
         * 1. Observer Network Konek atau Diskonek
         * 2. Observer jika status Logout atau (Tabel data pada tabel:
         *      FUser, FSalesman, FDivision, FWarehouse) tidak ada
         */
        observeNetworkChange()

        observeUserOtentication()


    }



    /**
     * Teknik on Back Navigation akan berlaku
     * jika dari fragment maka panggil menggunakan
     * android.R.id.home
     */
//    override fun onSupportNavigateUp(): Boolean {
////        Toast.makeText(this, "Ngene iki " + navControllerOfNavGraphOfMainActivity.currentDestination .toString(), Toast.LENGTH_LONG).show()
//        return navControllerOfNavGraphOfMainActivity.navigateUp() || super.onSupportNavigateUp()
//    }

//    override fun onBackPressed() {
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


    fun observeNetworkChange() {
        onNetworkChange { isConnected ->
            if (isConnected) {
                binding.mainErrorTextView.visibility = View.GONE
            } else if (!isConnected) {
                binding.mainErrorTextView.text = "Tidak ada koneksi internet"
                binding.mainErrorTextView.visibility = View.VISIBLE
            }
        }
    }

    fun observeUserOtentication() {
        mainViewModel.userViewStateLive.observe(this, Observer { userViewState ->
            when(userViewState){
                is Resource.Loading ->{
                }
                is Resource.Success ->{
                    val data = userViewState.data

                    /**
                     * Karena sering telat maka kriterianya di jadikan dua saja
                     */
//                    if (data.fUser ==null || data.fSalesman ==null || data.fDivision ==null || data.fWarehouse ==null){
//                    if (
//                            (data.fUser ==null && data.fDivision ==null) ||
//                            (data.fUser ==null && data.fSalesman ==null) ||
//                            (data.fUser ==null && data.fWarehouse ==null)
//                            ){
                    if (data.fUser ==null || data.fUser!!.username.isNullOrEmpty() || data.fUser!!.username.isBlank() ){
                        /**
                         * Note: kalau menggunakan semuanya, pasti ada yang telat. sehingga
                         * Show Login pasti di jalankan
                         */
//                        Log.e(TAG, "#result Bawah: \n${data.fUser} \n  ${data.fSalesman} \n\n")

                        showLoginView()
                    }else {
                        mainViewModel.userViewState = data //Sukses dan ada datanya untuk penampilan datanya diserahkan ke fragment

                    }

                }
                is Resource.Failure ->{
                    showLoginView()
                }
            }
            binding.mainProgressBar.visibility = View.GONE
        })

    }

    fun showLoginView() {
        /**
         * Sering error memanggil ini
         * maka pada Fragment di gunakan juga antisipasi cadangan
         *
         * Noted: Sebetulnya navigasi itu adalah dari Fragment ke Fragment
         * untuk dari Activity ke Fragment idealnya cuma sekalai jalan
         *
         * Maka navigasi yang lain akan di taruh di DashBoardFragment
         */
        try {
            val action =
                    DashBoardFragmentDirections.actionDashBoardFragmentToLoginFragment()
            findNavController(R.id.nav_host_fragment_main).navigate(action)
        }catch (e: Exception){}


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MainActivity_Old.RE_LOGIN && resultCode == Activity.RESULT_OK) {
//            Log.d(TAG, "#result OKE MASUK ####")
            val resultObject: FUser = data!!.getParcelableExtra<FUser>(LoginActivity.EXTRA_OBJECT) as FUser

            //Password yang dipakai adalah passwordConfirm: Untuk seterusnya
            resultObject.passwordConfirm = resultObject.password

//            Toast.makeText(this, "${resultObject.username} >> ${resultObject.password} >> ${resultObject.passwordConfirm}", Toast.LENGTH_LONG).show()
            val netChecker = NetworkChecker(this)

//            if (netChecker.isConnected) {

                val observer = mainViewModel.getRemoteFUserByUser(resultObject)
                        .toObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .map { newFUser ->
                            newFUser.lastModified = Date()
                            newFUser.created = Date()
                            newFUser.modifiedBy = "bagus"
                            newFUser.isLocked = false

                            newFUser
                        }
                        .subscribe(
                                {
                                    val newFUser = it

                                    newFUser.lastModified = Date()
                                    newFUser.created = Date()
                                    newFUser.modifiedBy = "bagus"

                                    //kareana akan dipaki seterusnya
                                    newFUser.password = resultObject.password
                                    newFUser.passwordConfirm = resultObject.password

                                    //CREATE JIKA SAMA SAJA
                                    mainViewModel.getRemoteFDivisionAndSaveToCache(newFUser)
                                    mainViewModel.getRemoteFSalesmanAndSaveToCache(newFUser)
                                    mainViewModel.getRemoteFWarehouseAndSaveToCache(newFUser)

                                    //Ketika Parent telat dalam Save maka
                                    mainViewModel.insertCacheFUser(newFUser)


                                },
                                {
//                                Log.d(TAG, "#result Error")
//                                Timber.e("Get repo error: $it")
//                                mainViewModel.setThrowable(it)

                                    showLoginView()
                                },
                                {
                                }
                        )

                compositeDisposable.add(observer)

//            }else {
//                Toast.makeText(this,R.string.error_check_internet_connection, Toast.LENGTH_SHORT).show()
//            }

        } else if (requestCode == MainActivity_Old.EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
//            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
//            if (mainViewModel.userEntityActive.id ==0){
//                showLoginView()
//            }

        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dashboard_refresh -> {
                refresh()
                true
            }
            R.id.dashboard_keluar -> {
                logOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun refresh() {
    }

    fun logOut() {
        val alert =
                AlertDialogConfirm(
                        this,
                        "Keluar dari Aplikasi?"
                )
        alert.getButtonOke().setOnClickListener(View.OnClickListener { view: View? ->
            alert.dismiss()

            mainViewModel.deleteCacheAllFUserAndAllParentRelationship()

        })
        alert.getButtonCancel()
                .setOnClickListener(View.OnClickListener {view: View? ->
                    alert.dismiss()
                })
        alert.showDialog()
    }


    companion object {
        const val RE_LOGIN = 1
        const val EDIT_REQUEST = 2
        const val TO_SUBMENU_REQUEST = 3
    }

}