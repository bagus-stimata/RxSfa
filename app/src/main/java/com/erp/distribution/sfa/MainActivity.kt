package com.erp.distribution.sfa

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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.erp.distribution.sfa.databinding.ActivityMainDashboardBinding
import com.erp.distribution.sfa.common_utils.AlertDialogConfirm
import com.erp.distribution.sfa.security_model.FUser
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    val mainViewModel: MainViewModel by viewModels<MainViewModel> ()
    lateinit var mainBinding: ActivityMainDashboardBinding

//    var apiAuthenticationClient: ApiAuthenticationClient? = null

//    var mainViewModel: MainViewModel? = null
//    var fMaterialViewModel: MaterialViewModel? = null
//    var fCustomerViewModel: CustomerViewModel? = null
    lateinit var userActive: FUser
    var progressBar: ProgressBar? = null

//    @BindView(R.id.greeting_img)
//    var greetImg: ImageView? = null
//
//    @BindView(R.id.greeting_text1)
//    var greetText1: TextView? = null
//
//    @BindView(R.id.greeting_text2)
//    var greetText2: TextView? = null
//
//    @BindView(R.id.home_mTopSyncronize)
//    var mTopSyncronize: RelativeLayout? = null
//
//    @BindView(R.id.home_mTopTopup)
//    var mTopTopup: RelativeLayout? = null
//
//    @BindView(R.id.home_mTopPromotion)
//    var mTopPromotion: RelativeLayout? = null
//
//    @BindView(R.id.home_mJourneyPlan)
//    var mJourneyPlan: CardView? = null
//
//    @BindView(R.id.home_mSalesOrder)
//    var mSalesOrder: CardView? = null
//
//    @BindView(R.id.home_mSummary)
//    var mSummary: CardView? = null
//
//    @BindView(R.id.home_mProduct)
//    var mProduct: CardView? = null
//
//    @BindView(R.id.home_mCustomer)
//    var mCustomer: CardView? = null
//
//    @BindView(R.id.home_mHelp)
//    var mHelp: CardView? = null
//
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_dashboard)

        mainBinding = ActivityMainDashboardBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

//        mainViewModel.refreshCacheFUser()

//        ButterKnife.bind(this)
//        mainViewModel = ViewModelProvider(this).get<MainViewModel>(MainViewModel::class.java)
//        fMaterialViewModel = ViewModelProvider(this).get(MaterialViewModel::class.java)
//        fCustomerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        /**
         * SECURITY BASIC CONFIG
         */
//        apiAuthenticationClient = ApiAuthenticationClient.getInstance()
        //        apiAuthenticationClient.setBaseUrl("http://192.168.1.100:8989/rest"); //di taruh API

            initialize()
            mainViewModel.loadCacheFUser()
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
    fun initialize() {
        initializeListener()
        if (mainViewModel.listFUser.size == 0) {
            showLoginView() //Setidaknya pernah masuk
        } else {
            /**
             * Init user otentikasi
             * Dari Dao
             */
//            userActive = mainViewModel!!.allFUser!![0]
//            apiAuthenticationClient.setUserActive(userActive)
//            apiAuthenticationClient.setUsername(userActive.username)
//            //            apiAuthenticationClient.setPassword(userActive.getPassword());
//            apiAuthenticationClient.setPassword(userActive.plainPassword)
//            apiAuthenticationClient.setBaseUrl("http://ssp-surabaya.ddns.net:8989/rest/")
//            mainViewModel.userActive = userActive
//            mainViewModel.username = userActive.username
//            mainViewModel.password = userActive.password

            showDashBoard()
        }
    }

    fun showDashBoard() {
        //Asumsi pada tabel user cuma satu
        greeting()
    }

    fun showLoginView() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivityForResult(intent, RE_LOGIN)
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RE_LOGIN && resultCode == Activity.RESULT_OK) {
            val resultObject: FUser = data!!.getSerializableExtra(LoginActivity.EXTRA_OBJECT) as FUser
//            apiAuthenticationClient = ApiAuthenticationClient.getInstance()
//            apiAuthenticationClient.setUsername(resultObject.username)
//            apiAuthenticationClient.setPassword(resultObject.password)
//            apiAuthenticationClient.setBaseUrl("http://192.168.1.100:8989/rest/")
            //            apiAuthenticationClient.setUsername("bagus");
//            apiAuthenticationClient.setPassword("hacker");
//            val fUserServiceRest = FUserServiceRest(this)

//            val domain: FUser = fUserServiceRest.getFUserByUsername(resultObject.username)
//            if (domain.username.isEmpty() || domain.password.isEmpty()) {
//                Toast.makeText(this, "Invalid Username atau Password", Toast.LENGTH_LONG).show()
//                showLoginView()
//            } else {
//                mainViewModel!!.deleteAllFUser()
//                domain.plainPassword =
//                    resultObject.password //Password yang dipakai untuk logi ke server: Adanya di android saja
//                mainViewModel!!.insert(domain)
//                showDashBoard()
//                Toast.makeText(this, "User Saved", Toast.LENGTH_LONG).show()
//            }

            mainViewModel.fetchRemoteFUser(resultObject)
//            mainViewModel.syncronizeUser(resultObject)

        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
//            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Else not saved", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun greeting() {
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar[Calendar.HOUR_OF_DAY]
        val namaUser: String = userActive.fullName
        if (userActive.fullName.isEmpty()) userActive.username
        if (timeOfDay >= 0 && timeOfDay < 12) {
            mainBinding.greetingText1.setText("Selamat Pagi")
            mainBinding.greetingImg!!.setImageResource(R.drawable.img_default_half_morning)
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            mainBinding.greetingText1.setText("Selamat Siang")
            mainBinding.greetingImg!!.setImageResource(R.drawable.img_default_half_afternoon)
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            mainBinding.greetingText1.setText("Selamat Sore")
            mainBinding.greetingImg!!.setImageResource(R.drawable.img_default_half_without_sun)
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            mainBinding.greetingText1.setText("Selamat Malam")
            mainBinding.greetingText1.setTextColor(Color.WHITE)
            mainBinding.greetingImg!!.setImageResource(R.drawable.img_default_half_night)
        }
        mainBinding.greetingText2.setText(namaUser)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dashboard_refresh -> true
            R.id.dashboard_keluar -> {
                logOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun logOut() {
        val alert = AlertDialogConfirm(this, "Keluar dari Aplikasi?")
        alert.getButtonOke().setOnClickListener(View.OnClickListener { view: View? ->
            mainViewModel!!.deleteAllFUser()
//            userActive = FUser()
            showLoginView()
        })
        alert.getButtonCancel()
            .setOnClickListener(View.OnClickListener { view: View? -> alert.dismiss() })
        alert.showDialog()
    }

    fun initializeListener() {
//        mTopSyncronize.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
//        mTopTopup.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
//        mTopPromotion.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
//
//
//
//        mJourneyPlan.setOnClickListener((View v) -> {
//            Toast.makeText(this, "Hello Journey Plan", Toast.LENGTH_LONG).show();
//        });
//        mSalesOrder.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//
//        });
//        mSummary.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//
//        });
//        mProduct.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//
//        });
//        mCustomer.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
//        mHelp.setOnClickListener((View v) -> {
//            aksiMenuDashboard(v);
//        });
    }

    fun aksiMenuDashboard(v: View) {
//        if (v.id == R.id.home_mTopSyncronize) {
//        } else if (v.id == R.id.home_mTopTopup) {
//        } else if (v.id == R.id.home_mTopPromotion) {
//        } else if (v.id == R.id.home_mJourneyPlan) {
//        } else if (v.id == R.id.home_mSalesOrder) {
//        } else if (v.id == R.id.home_mSummary) {
//        } else if (v.id == R.id.home_mProduct) {
//        } else if (v.id == R.id.home_mCustomer) {
//        } else if (v.id == R.id.home_mHelp) {
//        }
    }

//    @OnClick(R.id.home_mTopSyncronize)
    fun menuSyncronize() {
        val alert = AlertDialogConfirm(
            this,
            "Lanjutkan Syncronisasi dengan Server (membutuhkan waktu yang agak lama)"
        )
        alert.getButtonOke().setOnClickListener(View.OnClickListener { view: View? ->
//            val intent = Intent(this@MainActivity, SyncronizeActivity::class.java)
//            intent.putExtra(SyncronizeActivity.EXTRA_OBJECT, userActive)
//            startActivity(intent)
            alert.dismiss()
        })
        alert.getButtonCancel()
            .setOnClickListener(View.OnClickListener { view: View? -> alert.dismiss() })
        alert.showDialog()
    }

//    @OnClick(R.id.home_mSalesOrder)
    fun menuSalesOrder() {
//        val intent = Intent(this@MainActivity, SalesOrderActivity::class.java)
//        startActivity(intent)
    }

//    @OnClick(R.id.home_mProduct)
    fun menuProduct() {
//        val intent = Intent(this@MainActivity, MaterialActivity::class.java)
//        startActivity(intent)
    }

//    @OnClick(R.id.home_mCustomer)
    fun menuCustomer() {
//        val intent = Intent(this@MainActivity, CustomerActivity::class.java)
//        startActivity(intent)
    }

    companion object {
        const val RE_LOGIN = 1
        const val EDIT_NOTE_REQUEST = 2
        const val TO_SUBMENU_REQUEST = 3
    }
}