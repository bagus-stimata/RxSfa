package com.erp.distribution.sfa.presentation.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.erp.distribution.sfa.BR
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.data.source.entity_security.FUser
import com.erp.distribution.sfa.databinding.DashBoardFragmentBinding
import com.erp.distribution.sfa.presentation.base.BaseFragment
import com.erp.distribution.sfa.presentation.base.Resource
import com.erp.distribution.sfa.presentation.ui.customer.CustomerActivity
import com.erp.distribution.sfa.presentation.ui.customer.customer_list.CustomerFragmentDirections
import com.erp.distribution.sfa.presentation.ui.customer.customer_list.CustomerViewModel
import com.erp.distribution.sfa.presentation.ui.material.FMaterialActivity
import com.erp.distribution.sfa.presentation.ui.salesorder.FtSaleshActivity
import com.erp.distribution.sfa.presentation.ui.syncronize_fromserver.SyncronizeActivity
import com.erp.distribution.sfa.presentation.ui.utils.AlertDialogConfirm
import com.erp.distribution.sfa.presentation.ui.utils.AlertDialogWarning
import com.erp.distribution.sfa.utils.autoCleared
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect


class DashBoardFragment : BaseFragment<DashBoardFragmentBinding, MainViewModel>() {

    private val TAG = DashBoardFragment::class.java.simpleName

    override val bindingVariable: Int
        get() = BR.mainViewModel
    override val  viewModel: MainViewModel by viewModels<MainViewModel> ()

    override val layoutId: Int
        get() = R.layout.dash_board_fragment


    lateinit var viewBinding: DashBoardFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dash_board_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = DashBoardFragmentBinding.bind(view)
        viewBinding.activity = this


    }

    fun setupObserver() {
        viewModel.listUserActiveLive?.observe(this, androidx.lifecycle.Observer  {
            when (it) {
                is Resource.Loading -> {
//                    binding.emptyContainer.root.hide()
                }
                null, emptyList<FUser>() -> {
                    Log.d(TAG, "#result Empty Listen Dipanggil")
//                    showLoginView()
                }
                is Resource.Success -> {
                    if (it.data!!.isEmpty()) {
                    }else {
                        viewModel.userActive = it.data.get(0)

//                        subscribeRemoteFDivision(mainViewModel.userActive)
//                        subscribeRemoteFSalesman(mainViewModel.userActive)
//                        subscribeRemoteFWarehouse(mainViewModel.userActive)
                    }

                }
                else -> {
//                    viewModel.userActive = it.get(0)
//                    mainViewModel.getRemoteFSalesman(mainViewModel.userActive)
                    if(viewModel.userActive.id >0) {
                        /**
                         * Lihat Masih dari Remote
                         */
//                        subscribeRemoteFDivision(mainViewModel.userActive)
//                        subscribeRemoteFSalesman(mainViewModel.userActive)
//                        subscribeRemoteFWarehouse(mainViewModel.userActive)
                    }
                    Log.d(TAG, "#result userActive: ${viewModel.userActive.fdivisionBean}" +
                            " and ${viewModel.userActive.fsalesmanBean} and ${viewModel.userActive.fwarehouseBean}")
//                    greeting()
                }
            }
            viewBinding.userActive = viewModel.userActive

        })
    }




    fun menuSyncronize() {
    }

    fun menuSalesOrder() {
    }

    fun menuProduct() {

        val action = DashBoardFragmentDirections.actionDashBoardFragmentToFMaterialFragment(
                FUser()
        )
        findNavController().navigate(action)

//        val action = CustomerFragmentDirections.actionCustomerFragmentToCustomerFragmentAddEdit(
//                null,
//                "New Customer"
//            )
//      findNavController().navigate(action)
    }

    fun menuCustomer() {

        val action = DashBoardFragmentDirections.actionDashBoardFragmentToFCustomerFragment(
                FUser()
        )
        findNavController().navigate(action)

    }



    override fun onDestroyView() {
        super.onDestroyView()
//        searchView.setOnQueryTextListener(null)
    }



}