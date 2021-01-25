package com.erp.distribution.sfa.presentation.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.ActivityTestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {

    private val TAG = AppCompatActivity::class.java.simpleName

    val viewModel: TestViewModel by viewModels<TestViewModel> ()
    lateinit var mainBinding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        mainBinding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

//        val str: String? = null
//        val str: String? = "aselole"
//        str?.let {
//            Log.d("result",  "Test Let ${str}")
//        }

//        viewModel.test()
//        viewModel.getRetrieveRemoteData()
//        viewModel.createFArea()

//        viewModel.addCacheData()
//        viewModel.deleteAllCacheData()

//        viewModel.domainData .observe(this, {
//            mainBinding.apply {
//
//                it?.let {
//                    if (it.isNotEmpty()) {
//                        textTest.text = "yes Bos ${it[0].username}"
//                    }
//                    Log.d("result", "Hello Tidak Null, tapi kemungkinan kosong")
//                    it.reversed()
//                }.also {
//                    Log.d("result", "ditulis juka karena tidak null")
//                }
//
//            }
//        })

//        viewModel.dataFArea.observe(this, {
//            mainBinding.apply {
//
//                it?.let {
//                    if (it.isNotEmpty()) {
////                        textTest.text = "yes Bos ${it[0].username}"
//                        textTest.text = it.toString()
//                        it
//                    }
//                }.also {
//                }
//
//            }
//        })

        viewModel.listenNotesResult() .observe(this, {
            mainBinding.apply {

                it?.let {
                    if (it.isNotEmpty()) {
//                        textTest.text = "yes Bos ${it[0].username}"
                        textTest.text = it.toString()
                        it
                        Log.d(TAG, "#result : ${it.size}")
                    }
                }.also {
                }

            }
        })

        viewModel.test()


    }

}