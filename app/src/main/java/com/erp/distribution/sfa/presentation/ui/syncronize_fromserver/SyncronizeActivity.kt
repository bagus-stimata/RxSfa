package com.erp.distribution.sfa.presentation.ui.syncronize_fromserver

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.ActivitySyncronizeBinding
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.data.source.entity.FDivisionEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3Entity
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

    val viewModel: SyncViewModel by viewModels<SyncViewModel> ()
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

//        viewModel.subscribeFMaterialFromRepo()
//        viewModel.subscribeCustomerFromRepo() pakai bawah bos cara lain
    }

    fun setupObservable() {

        val observerFDivision = viewModel.getFDivisionById_FromRepo()
                .map {
                        it.modified = Date()
                        it.created = Date()
                        it.modifiedBy = viewModel.userActive.username
                        it.isStatusActive=true
                        it

                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            viewModel.subscribeListFdivisionByParent_FromRepo(it)
                        },
                        {
                            Log.d(TAG, "#result Fetch FDivision error  ${it.message}")
                        },
                        {
                        }
                )

        compositeDisposable.add(observerFDivision)


        viewModel.getCacheFMaterialGroup3Live().observe(this, Observer {
            when (it) {
                null, emptyList<FMaterialGroup3Entity>() -> {
//                    viewModel.checkList1 = "trying.. Sync Material/Product (empty)"
                }
                else -> {
//                    viewModel.checkList1 = "Product Selesai, sejumlah ${it.size} items"
//                    if (!viewModel.checkList1.contains("trying..")) {
//                        binding.progressBar.setIndeterminate(false)
//                        var currentProcess = binding.progressBar.progress +10
//                        binding.progressBar.progress = currentProcess
//                        binding.progressText.text = "${currentProcess}%"
//                        if (currentProcess ==100){
//                            viewModel.isLoading = false;
//                        }
//                    }
                }
            }
            binding.masterViewModel = this.viewModel
        })
        val observerFMaterialGroup3 = viewModel.getFMaterialGroup3FromRepo()
                .map { data ->
                    data.map {
                        it.modified = Date()
                        it.created = Date()
                        it.modifiedBy = viewModel.userActive.username
                        it.isStatusActive=false
                        it
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
//                            Log.d(TAG, "#result Ready To Insert FMaterialGroup3  ${it}")
                            viewModel.insertCacheFMaterialGroup3(it as List<FMaterialGroup3Entity>)
                        },
                        {
                            Log.d(TAG, "#result Fetch FMaterialGroup3 error  ${it.message}")
                        },
                        {
                        }
                )

        compositeDisposable.add(observerFMaterialGroup3)





        setupObservableFMaterial()
//        setupObservableFCustomer()


    }

    fun setupObservableFMaterial() {

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

        val observerFMaterial = viewModel.getFMaterialFromRepo()
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
                            viewModel.insertCacheFMaterial(it as List<FMaterialEntity>)
                            Log.d(TAG, "#result InsertCache FMaterial Success")
                        },
                        {
                            Log.d(TAG, "#result InsertCache FMaterial error  ${it.message}")
                        },
                        {

                        }
                )
        compositeDisposable.add(observerFMaterial)


    }

    fun setupObservableFCustomer() {
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