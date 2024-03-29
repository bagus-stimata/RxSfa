package com.erp.distribution.sfa.presentation.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.ActivityMainDashboardBinding
import com.erp.distribution.sfa.presentation.ui.utils.AlertDialogConfirm
import com.erp.distribution.sfa.presentation.ui.syncronize_fromserver.SyncronizeActivity
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.presentation.ui.customer.CustomerActivity
import com.erp.distribution.sfa.presentation.ui.material.FMaterialActivity
import com.erp.distribution.sfa.presentation.ui.salesorder.FtSaleshActivity
import com.erp.distribution.sfa.utils.DisposableManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.*


@AndroidEntryPoint
class MainActivity_Old : AppCompatActivity() {
    private val TAG = MainActivity_Old::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()

    val mainViewModelOld: MainViewModel_Old by viewModels<MainViewModel_Old> ()
    lateinit var mainBinding: ActivityMainDashboardBinding

    var progressBar: ProgressBar? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_dashboard)

//        mainBinding = ActivityMainDashboardBinding.inflate(layoutInflater)
//        setContentView(mainBinding.root)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_dashboard)

        mainBinding.activity = this
        mainBinding.userActive = mainViewModelOld.userEntityActive
        mainBinding.divisionActive = mainViewModelOld.divisionEntityActive
        mainBinding.salesmanActive = mainViewModelOld.salesmanEntityActive
        mainBinding.warehouseActive = mainViewModelOld.warehouseEntityActive

        setupObserver()

    }

    /**
     * Konsep:
     * A. Ketika Login -> Wajib Menggunakan Otentikasi dari Server
     * B. Ketika Berhasil Login maka aplikasi akan tetap bisa digunakan walau tanpa koneksi internet sampai Logout
     * (setidaknya pernah login, sudah bisa menggunakan aplikasi)
     *
     * 1. Ketika masuk aplikasi: Maka periksa apakah username dan password ada dalam database (biar salah atau benar)
     * A. Jika ada maka aplikasi bisa dipakai (walau username dan database salah)
     * B. Jika belum ada maka akan dipaksa untuk Login
     *
     * 2. Login -> Ambil UserName dan Password dari Database -> Jika Ada maka simpan kedalam database
     * 3. User pada database akan dihapus jika: (a) Logout (b) Berhasil Login (diganti dengan data user baru)
     */
    fun setupObserver() {

        mainViewModelOld.listUserEntityActiveLive?.observe(this, androidx.lifecycle.Observer  {
            when (it) {
                null, emptyList<FUserEntity>() -> {
                    Log.d(TAG, "#result Empty Listen Dipanggil")
                    showLoginView()
                }
                else -> {
//                    mainViewModel.userActive = it.get(0)
//                    mainViewModel.getRemoteFSalesman(mainViewModel.userActive)
                    if(mainViewModelOld.userEntityActive.id >0) {
                        /**
                         * Lihat Masih dari Remote
                         */
                        subscribeRemoteFDivision(mainViewModelOld.userEntityActive)
                        subscribeRemoteFSalesman(mainViewModelOld.userEntityActive)
                        subscribeRemoteFWarehouse(mainViewModelOld.userEntityActive)
                    }
                    Log.d(TAG, "#result userActive: ${mainViewModelOld.userEntityActive.fdivisionBean}" +
                            " and ${mainViewModelOld.userEntityActive.fsalesmanBean} and ${mainViewModelOld.userEntityActive.fwarehouseBean}")
                    greeting()
//                    Log.d(TAG, "#result Listen Dipanggil ${MainApplication.authHeader}")
                }
            }
            mainBinding.userActive = mainViewModelOld.userEntityActive

        })



    }

    fun subscribeRemoteFDivision(fUserEntity: FUserEntity) {
        DisposableManager.add(mainViewModelOld.getRemoteFDivision(fUserEntity)
            .toObservable()
            .map {
                it.isStatusActive = true
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    mainViewModelOld.divisionEntityActive = it
                    mainViewModelOld.insertCacheFDivision(it)
                    mainBinding.divisionActive = mainViewModelOld.divisionEntityActive

                },
                {

                    Log.d(TAG, "#result salesman error ${it.message}")
                    Timber.e("Get repo error: $it")
//                    mainViewModel.setThrowable(it)

                },
                {
                }
            )
        )
    }
    fun subscribeRemoteFSalesman(fUserEntity: FUserEntity) {
        DisposableManager.add(mainViewModelOld.getRemoteFSalesman(fUserEntity)
            .toObservable()
            .map {
                it.statusActive = true
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    mainViewModelOld.salesmanEntityActive = it
                    mainViewModelOld.insertCacheFSalesman(it)
                    mainBinding.salesmanActive = mainViewModelOld.salesmanEntityActive

                },
                {
                    Log.d(TAG, "#result salesman error ${it.message}")
                },
                {

                }
            )
        )
    }


    fun subscribeRemoteFWarehouse(fUserEntity: FUserEntity) {
        DisposableManager.add(mainViewModelOld.getRemoteFWarehouse(fUserEntity)
            .toObservable()
            .map {
                it.statusActive = true
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    mainViewModelOld.warehouseEntityActive= it
                    mainViewModelOld.insertCacheFWarehouse(it)
                    mainBinding.warehouseActive = mainViewModelOld.warehouseEntityActive

                },
                {
                    Log.d(TAG, "#result salesman error ${it.message}")
                },
                {

                }
            )
        )
    }



//    fun showDashBoard() {
//        //Asumsi pada tabel user cuma satu
//        greeting()
//    }

    fun showLoginView() {
        mainViewModelOld.userEntityActive = FUserEntity()
        val intent = Intent(this@MainActivity_Old, LoginActivity::class.java)
        startActivityForResult(intent, RE_LOGIN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RE_LOGIN && resultCode == Activity.RESULT_OK) {
//            Log.d(TAG, "#result OKE MASUK ####")
            val resultObject: FUserEntity = data!!.getParcelableExtra<FUserEntity>(LoginActivity.EXTRA_OBJECT) as FUserEntity

//            Log.d(TAG, "#result OKE >> " + resultObject.username + " >> " + resultObject.password)
            //Password yang dipakai adalah passwordConfirm: Untuk seterusnya
            resultObject.passwordConfirm = resultObject.password

            val observer = mainViewModelOld.getRemoteFUserByUser(resultObject)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.lastModified = Date()
                        it.created = Date()
                        it.modifiedBy = "bagus"
                        it
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
                                mainViewModelOld.insertCacheFUser(newFUser)

                            },
                            {

                                Log.d(TAG, "#result Error")
                                Timber.e("Get repo error: $it")
//                                mainViewModel.setThrowable(it)

                                showLoginView()



                            },
                            {
                            }
                    )

            compositeDisposable.add(observer)


            val observerAll = mainViewModelOld.fetchFUserFromRepo()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map {
                    it
                }
                .subscribe(
                    {
//                        val newFUser = it
                        Log.d(TAG, "#result FUser Login trying add all ${it}")

                    },
                    {
                        Log.d(TAG, "#result FUser Login  error add all")
                    },
                    {
                    }
                )

            compositeDisposable.add(observerAll)



        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
//            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
           if (mainViewModelOld.userEntityActive.id ==0){
               showLoginView()
           }
//            Toast.makeText(this, "Else not saved", Toast.LENGTH_SHORT).show()
        }
    }



    @SuppressLint("SetTextI18n")
    private fun greeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar[Calendar.HOUR_OF_DAY]
//        val namaUser: String = mainViewModel.userActive.fullName
        if (mainViewModelOld.userEntityActive.fullName.isEmpty()) mainViewModelOld.userEntityActive.username
        if (timeOfDay >= 0 && timeOfDay < 12) {
//            mainBinding.greetingText1.setText("Selamat Pagi")
            mainBinding.greetingImg.setImageResource(R.drawable.img_default_half_morning)
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
//            mainBinding.greetingText1.setText("Selamat Siang")
            mainBinding.greetingImg.setImageResource(R.drawable.img_default_half_afternoon)
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
//            mainBinding.greetingText1.setText("Selamat Sore")
            mainBinding.greetingImg.setImageResource(R.drawable.img_default_half_without_sun)
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
//            mainBinding.greetingText1.setText("Selamat Malam")
            mainBinding.greetingText1.setTextColor(Color.WHITE)
            mainBinding.greetingImg.setImageResource(R.drawable.img_default_half_night)
        }
//        mainBinding.greetingText2.setText(namaUser)
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

            mainViewModelOld.deleteCacheAllFUser()

        })
        alert.getButtonCancel()
            .setOnClickListener(View.OnClickListener {view: View? ->
                alert.dismiss()
            })
        alert.showDialog()
    }

    fun menuSyncronize() {
        val alert =
            AlertDialogConfirm(
                this,
                "Lanjutkan Syncronisasi dengan Server (membutuhkan waktu yang agak lama)"
            )
        alert.getButtonOke().setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this@MainActivity_Old, SyncronizeActivity::class.java)

            intent.putExtra(SyncronizeActivity.EXTRA_USERACTIVE, mainViewModelOld.userEntityActive)
            intent.putExtra(SyncronizeActivity.EXTRA_DIVISIONACTIVE, mainViewModelOld.divisionEntityActive)

            startActivity(intent)
            alert.dismiss()
        })
        alert.getButtonCancel()
            .setOnClickListener(View.OnClickListener { view: View? -> alert.dismiss() })
        alert.showDialog()
    }

    fun menuSalesOrder() {
        val intent = Intent(this@MainActivity_Old, FtSaleshActivity::class.java)
        startActivity(intent)

    }

    fun menuProduct() {
        val intent = Intent(this@MainActivity_Old, FMaterialActivity::class.java)
        startActivity(intent)
    }

    fun menuCustomer() {
        val intent = Intent(this@MainActivity_Old, CustomerActivity::class.java)
        startActivity(intent)
    }



    companion object {
        const val RE_LOGIN = 1
        const val EDIT_NOTE_REQUEST = 2
        const val TO_SUBMENU_REQUEST = 3
    }
}