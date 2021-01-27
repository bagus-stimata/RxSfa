package com.erp.distribution.sfa.presentation.ui.master.syncronize_fromserver

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.ActivitySyncronizeBinding
import com.erp.distribution.sfa.presentation.ui.master.MasterViewModel
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity_security.FUser
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

@AndroidEntryPoint
class SyncronizeActivity : AppCompatActivity() {
    private val TAG = SyncronizeActivity::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()

    val viewModel: MasterViewModel by viewModels<MasterViewModel> ()
    lateinit var binding: ActivitySyncronizeBinding


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syncronize)

        binding = ActivitySyncronizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activity = this
        binding.masterViewModel = this.viewModel

        val intent: Intent = getIntent()
        if (intent.hasExtra(EXTRA_OBJECT)) {
            viewModel.userActive = intent.getSerializableExtra(EXTRA_OBJECT) as FUser
        }

        binding.progressBar.max = 100

        setupObservable()

        viewModel.subscribeFMaterialFromRepo()
//        viewModel.subscribeCustomerFromRepo() pakai bawah bos cara lain
    }

    fun setupObservable() {


        viewModel.getCacheFMaterialLive() .observe(this, Observer {
            when (it) {
                null, emptyList<FMaterialEntity>() -> {
                    viewModel.checkList1 = "trying.. Sync Material/Product (empty)"
                }
                else -> {
                    viewModel.checkList1 = "Product Selesai, sejumlah ${it.size} items"
                    if (!viewModel.checkList1.contains("trying..")) {
                        binding.progressBar.setIndeterminate(false)
                        var currentProcess = binding.progressBar.progress +50
                        binding.progressBar.progress = currentProcess
                        binding.progressText.text = "${currentProcess}%"
                        if (currentProcess ==100){
                            viewModel.isLoading = false;
                        }

                    }
                }
            }
            binding.masterViewModel = this.viewModel
        })

        viewModel.listFMaterialEntityMutableLive.observe(this, Observer {
            when (it) {
                null, emptyList<FMaterialEntity>() -> {

                }
                else -> {
                    viewModel.insertCacheFMaterial(it)
                }
            }
        })



        viewModel.getCacheFCustomerLive().observe(this, Observer {
            when (it) {
                null, emptyList<FCustomerEntity>() -> {
                    viewModel.checkList2 = "trying.. Sync Customer (empty)"
                }
                else -> {
                    viewModel.checkList2 = "Customer Selesai, sejumlah ${it.size} items"
                    if (!viewModel.checkList2.contains("trying..")) {
                        binding.progressBar.setIndeterminate(false)
                        var currentProcess = binding.progressBar.progress +50
                        binding.progressBar.progress = currentProcess
                        binding.progressText.text = "${currentProcess}%"
                        if (currentProcess >=100){
                            viewModel.isLoading = false;
                        }

                    }
                }
            }
            binding.masterViewModel = this.viewModel
        })

//        viewModel.listFCustomerMutableLive.observe(this, Observer {
//            when (it) {
//                null, emptyList<FCustomer>() -> {
//
//                }
//                else -> {
//                    viewModel.insertCacheFCustomer(it)
//                }
//            }
//        })

        val observerCustomer = viewModel.getFCustomerFromRepo()
            .map { data ->
               data.map {
                    it.modified = Date()
                    it.created = Date()
                    it.modifiedBy = viewModel.userActive.username
                   it
               }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                    {
                        viewModel.insertCacheFCustomer(it as List<FCustomerEntity>)
                    },
                    {

                    },
                    {

                    }
            )

        compositeDisposable.add(observerCustomer)


    }

    fun kembali() {
        finish()
    }


    companion object {
        const val EXTRA_OBJECT = "com.erp.distribution.sfa.presentation.ui.master.SyncronizeActivity.EXTRA_OBJECT"
    }
}