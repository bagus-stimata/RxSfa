package com.erp.distribution.sfa.presentation.ui.syncronize_fromserver

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.data.source.entity.*
import com.erp.distribution.sfa.databinding.ActivitySyncronizeBinding
import com.erp.distribution.sfa.data.source.entity_security.FUser
import com.erp.distribution.sfa.domain.exception.Action
import com.erp.distribution.sfa.domain.exception.Redirect
import com.erp.distribution.sfa.presentation.extention.setVisible
import com.erp.distribution.sfa.presentation.extention.showDialog
import com.google.android.material.snackbar.Snackbar
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
        if (intent.hasExtra(EXTRA_USERACTIVE)) {
            viewModel.userActive = intent.getParcelableExtra<FUser>(EXTRA_USERACTIVE) as FUser
//            Log.d(TAG, "#result ParcelableExtra  ${viewModel.userActive.username}")
        }
        if (intent.hasExtra(EXTRA_DIVISIONACTIVE)) {
            viewModel.divisionActive = intent.getParcelableExtra<FDivisionEntity>(EXTRA_DIVISIONACTIVE) as FDivisionEntity
//            Log.d(TAG, "#result ParcelableExtra ${viewModel.divisionActive.kode1}")
        }

        binding.progressBar.max = 100

        setupObservable()

//        viewModel.subscribeFMaterialFromRepo()
//        viewModel.subscribeCustomerFromRepo() pakai bawah bos cara lain
    }

    fun setupObservable() {


        setupObservableFDivision()

        setupObservableArea()
        setupObservableFMaterialGroup3()
        setupObservableFCustomerGroup()

        setupObservableFMaterial()
        setupObservableFCustomer()
    }


    fun setupObservableFDivision() {
        val observerFDivision = viewModel.getFDivisionById_FromRepo()
            .map {
                it.modified = Date()
                it.created = Date()
                it.modifiedBy = viewModel.userActive.username
                it.isStatusActive = false
                if (it.id==viewModel.divisionActive.id) {
                    it.isStatusActive = true
                }

                it

            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    viewModel.subscribeListFdivisionByParent_FromRepo(it)
                },
                {
//                    Log.d(TAG, "#result Fetch FDivision error  ${it.message}")
                },
                {
                }
            )

        compositeDisposable.add(observerFDivision)


    }

    fun setupObservableArea(): Unit {
        val observerFArea = viewModel.getFAreaFromRepo()
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
                    viewModel.insertCacheFArea(it as List<FAreaEntity>)
                },
                {
                    Log.d(TAG, "#result Fetch FArea error  ${it.message}")
                },
                {
                }
            )

        compositeDisposable.add(observerFArea)

        viewModel.getCacheFAreaLive() .observe(this, Observer {
            when (it) {
                null, emptyList<FAreaEntity>() -> {
                }
                else -> {
                    it.iterator().forEach { data ->
                        viewModel.subscribeListFSubAreaByParent_FromRepo(data)
                    }
                }
            }
        })


    }
    
    fun setupObservableFMaterialGroup3(): Unit {
        val observerFMaterialGroup3 = viewModel.getFMaterialGroup3FromRepo()
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
                    viewModel.insertCacheFMaterialGroup3(it as List<FMaterialGroup3Entity>)
                },
                {
                    Log.d(TAG, "#result Fetch FMaterialGroup3 error  ${it.message}")
                },
                {
                }
            )

        compositeDisposable.add(observerFMaterialGroup3)

    }

    fun setupObservableFCustomerGroup(): Unit {
        val observerFCustomerGroup = viewModel.getFCustomerGroupFromRepo()
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
                            viewModel.insertCacheFCustomerGroup(it as List<FCustomerGroupEntity>)
                        },
                        {
                            Log.d(TAG, "#result Fetch FMaterialGroup3 error  ${it.message}")
                        },
                        {
                        }
                )

        compositeDisposable.add(observerFCustomerGroup)

    }

    fun setupObservableFMaterial() {

        viewModel.getCacheFMaterialLive().observe(this, Observer {
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
//            binding.executePendingBindings()
        })

        val observerFMaterial = viewModel.getFMaterialFromRepo()
                .map { data ->
                    data.map {
                        it.modified = Date()
                        it.created = Date()
                        it.modifiedBy = viewModel.userActive.username
                        if (it.expiredDate==null) it.expiredDate = Date()

                        it
                    }
                }
            .firstElement()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            viewModel.insertCacheFMaterial(it)
                            Log.d(TAG, "#result Get InsertCache FMaterial Success ")
                        },
                        {
//                            Log.d(TAG, "#result InsertCache FMaterial error  ${it.message}")
                            Log.d(TAG, "#result Get From  FMaterial error")
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
//            binding.executePendingBindings()
        })


        val observerCustomer = viewModel.getFCustomerFromRepo()
                .map { data ->
                    data.map {
                        it.modified = Date()
                        it.created = Date()
                        it.modifiedBy = viewModel.userActive.username
                        /**
                         * Yang Null
                         */

                        it
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            viewModel.insertCacheFCustomer(it as List<FCustomerEntity>)
//                            Log.d(TAG, "#result Get FCustomer Success: ${it.size}")
                        },
                        {
                            Log.d(TAG, "#result Get FCustomer error ${it.message}")
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
//        const val EXTRA_OBJECT = "com.erp.distribution.sfa.presentation.ui.master.SyncronizeActivity.EXTRA_OBJECT"
        const val EXTRA_USERACTIVE = "com.erp.distribution.sfa.presentation.ui.master.SyncronizeActivity.EXTRA_USERACTIVE"
        const val EXTRA_DIVISIONACTIVE = "com.erp.distribution.sfa.presentation.ui.master.SyncronizeActivity.EXTRA_DIVISIONACTIVE"
        const val EXTRA_SALESMANACTIVE = "com.erp.distribution.sfa.presentation.ui.master.SyncronizeActivity.EXTRA_SALESMANACTIVE"
        const val EXTRA_WAREHOUSEACTIVE = "com.erp.distribution.sfa.presentation.ui.master.SyncronizeActivity.EXTRA_WAREHOUSEACTIVE"
    }


}