package com.erp.distribution.sfa.master.syncronize_fromserver

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.ActivitySyncronizeBinding
import com.erp.distribution.sfa.master.MasterViewModel
import com.erp.distribution.sfa.security_model.FUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SyncronizeActivity : AppCompatActivity() {

    val viewModel: MasterViewModel by viewModels<MasterViewModel> ()
    lateinit var binding: ActivitySyncronizeBinding

    var userActive: FUser = FUser()

//    @BindView(R.id.progress_bar)
//    var progressBar: ProgressBar? = null

//    @BindView(R.id.progress_text)
//    var progressText: TextView? = null

//    @BindView(R.id.detil_info)
//    var detilInfo: TextView? = null

//    @BindView(R.id.progress_btn_selesai)
//    var btnSelesai: Button? = null
//    var counter = 0
//    var persentase = 0
//    var fMaterialServiceRest: FMaterialServiceRest? = null
//    var fCustomerServiceRest: FCustomerServiceRest? = null
//    var listFMaterial: List<FMaterial> = ArrayList<FMaterial>()
//    var listFCustomer: List<FCustomer> = ArrayList<FCustomer>()

//    var allDataSize = 0
//    var thread1: Thread? = null
//    var thread2: Thread? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syncronize)

        binding = ActivitySyncronizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = getIntent()
        if (intent.hasExtra(EXTRA_OBJECT)) {
            userActive = intent.getSerializableExtra(EXTRA_OBJECT) as FUser
        }
        binding.detilInfo.setText("Sedang menarik data dari server...")

        setupObservable()

    }

    fun setupObservable() {
//        viewModel.fetchCustomerFromRemote()
//        viewModel.doSync_MaterialFromServer()

    }

    fun startInsertToDb() {
//        if (listFMaterial.size > 0 && listFCustomer.size > 0) {
//            allDataSize = listFCustomer.size + listFMaterial.size
//            progressBar.setMax(allDataSize)
//            progressBar.setIndeterminate(false)
//            for (domain in listFMaterial) {
//                val insertTask = InsertFMaterialAsyncTask(apiAuthenticationClient, domain)
//                insertTask.execute()
//            }
//            for (domain in listFCustomer) {
//                val insertTask = InsertFCustomerAsyncTask(apiAuthenticationClient, domain)
//                insertTask.execute()
//            }
//        }

    }

    fun progressStatus() {
//        progressBar.setProgress(counter)
//        //            progressText.setText( ((progressBar.getProgress() /progressBar.getMax())*100) + "");
//        progressText.setText(progressBar.getProgress().toString() + "")
//        detilInfo.setText("Save Data: $counter of $allDataSize")
//        if (progressBar.getProgress() == progressBar.getMax()) btnSelesai!!.visibility =
//            View.VISIBLE
    }

//    inner class InsertFMaterialAsyncTask(
//        apiAuthenticationClient: ApiAuthenticationClient?,
//        domain: FMaterial
//    ) : AsyncTask<Void?, Void?, Int?>() {
//        private val apiAuthenticationClient: ApiAuthenticationClient?
//        var domain: FMaterial = FMaterial()
//        protected override fun onPreExecute() {}
//        protected override fun doInBackground(vararg voids: Void): Int {
//            fMaterialViewModel.insert(domain)
//            counter++
//            return 0
//        }
//
//        protected override fun onPostExecute(result: Int) {
//            progressStatus()
//        }
//
//        init {
//            this.apiAuthenticationClient = apiAuthenticationClient
//            this.domain = domain
//        }
//    }

//    inner class InsertFCustomerAsyncTask(
//        apiAuthenticationClient: ApiAuthenticationClient?,
//        domain: FCustomer
//    ) : AsyncTask<Void?, Void?, Int?>() {
//        private val apiAuthenticationClient: ApiAuthenticationClient?
//        var domain: FCustomer = FCustomer()
//        protected override fun onPreExecute() {}
//        protected override fun doInBackground(vararg voids: Void): Int {
//            fCustomerViewModel.insert(domain)
//            counter++
//            return 0
//        }
//
//        protected override fun onPostExecute(result: Int) {
//            progressStatus()
//        }
//
//        init {
//            this.apiAuthenticationClient = apiAuthenticationClient
//            this.domain = domain
//        }
//    }

    companion object {
        const val EXTRA_OBJECT = "com.erp.distribution.sfa.master.SyncronizeActivity.EXTRA_OBJECT"
    }
}