package com.erp.distribution.sfa.presentation.ui.syncronize_fromserver

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.FragmentSyncronizeBinding
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.domain.model.toEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class SyncronizeFragment : Fragment(R.layout.fragment_syncronize) {

    private val TAG = SyncronizeFragment::class.java.simpleName

    val  syncViewModel: SyncViewModel by viewModels<SyncViewModel> ()

    lateinit var viewBinding: FragmentSyncronizeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSyncronizeBinding.bind(view)

        viewBinding.actionFragment = this
        viewBinding.syncViewModel = syncViewModel
        viewBinding.isLoading = false

        viewBinding.apply {
//             checkList1.text = syncViewModel!!.fUser!!.username
        }

//        Log.d(TAG, "#resul parameter pass ${syncViewModel.fUser!!.username}")
//        Toast.makeText(context, "#resul parameter pass ${syncViewModel.fUser!!.username}", Toast.LENGTH_SHORT).show()

        syncViewModel.progresPersenLive.observe(viewLifecycleOwner, Observer {
            viewBinding.progressBar.progress = it
            viewBinding.progressText.text = it.toString()
            if (it >=100) {
                viewBinding.isLoading = false
                viewBinding.textView3.text = "Start Sync"
            }
        })

        syncViewModel.progresMessageSuccessLive.observe(viewLifecycleOwner, Observer {
            it?.let { viewBinding.checkList1.text = it }
        })
        syncViewModel.progresMessageErrorLive.observe(viewLifecycleOwner, Observer {
            it?.let {  viewBinding.checkList2.text = it }
        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            syncViewModel.syncEventFlow.collect { event ->
                when (event){
                    is SyncViewModel.SyncFragmentEvent.ShowInvalidInputMessage -> {
                        Toast.makeText(context, event.msg, Toast.LENGTH_SHORT).show()
                    }
                    is SyncViewModel.SyncFragmentEvent.StartSyncFromRepo -> {
                        val fUserEntity = event.userViewState.fUser!!.toEntity()
                        val fDivisionEntity = event.userViewState.fDivision!!.toEntity()

                        syncViewModel.progresPersenLive.postValue(0)

                        syncViewModel.getFMaterialGroup123FromRepoAndSaveToCache(fUserEntity, fDivisionEntity)

                        syncViewModel.getFAreaFromRepoAndSaveToCache(fUserEntity, fDivisionEntity)
                        syncViewModel.getFCustomerGroupFromRepoAndSaveToCache(fUserEntity, fDivisionEntity)
                        syncViewModel.getFCustomerFromRepoAndSaveToCache(fUserEntity, fDivisionEntity)

                        syncViewModel.getFMaterialFromRepoAndSaveToCache(fUserEntity, fDivisionEntity)

                    }
                    is SyncViewModel.SyncFragmentEvent.NavigateBackWithResult -> {
                        val fUser = FUser()
                        setFragmentResult(
                                "fromLoginFragment_requestKey",
                                bundleOf("fromLoginFragment_resultKey" to  fUser)
                        )
                        findNavController().popBackStack()

                    }
                }
            }
        }

        /**
         * Jika Fungsi ini dipasang pada Fragment maka
         * 1. PopBackStack akan dilakukan secara manual
         * 2. Jika findNavController().popBackStack() dinon aktifkan maka tidak akan bisa melakukan BackPress
         */
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (syncViewModel.isLoading == false) {
                    myPopBackStack()
                }
//                findNavController().popBackStack()
//                Toast.makeText(context, "hello", Toast.LENGTH_LONG).show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        initialize()
        /**
         * Mangaktifkan Semua Option Menu
         */
        setHasOptionsMenu(true)

    }

    fun initialize() {
    }

    fun startStopSync() {
        viewBinding.textView3.text = "Proses.."
        viewBinding.isLoading = true

        syncViewModel.startSync(syncViewModel.userViewState!!)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home ->{
                if (syncViewModel.isLoading==false) {
                    myPopBackStack()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun myPopBackStack() {
        //Jika masih loading tidak boleh kembali
        if (! syncViewModel.isLoading) {
            findNavController().popBackStack()
        }else {
            Toast.makeText(context, "Masih proses loading..", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}