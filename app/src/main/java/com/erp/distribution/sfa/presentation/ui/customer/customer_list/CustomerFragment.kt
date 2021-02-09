package com.erp.distribution.sfa.presentation.ui.customer.customer_list

import android.os.Bundle
import com.erp.distribution.sfa.R
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.databinding.FragmentCustomerBinding
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.presentation.ui.utils.AlertDialogWarning
import com.erp.distribution.sfa.presentation.ui.utils.onQueryTextChanged
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomerFragment : Fragment(R.layout.fragment_customer), CustomerAdapter.OnItemClickListener {

    private val viewModel: CustomerViewModel by viewModels()

    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCustomerBinding.bind(view)

        val customerAdapter = CustomerAdapter(this)


        binding.apply {
            recyclerViewCustomer.apply {
                adapter = customerAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }


//            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
//                0,
//                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//            ) {
//                override fun onMove(
//                    recyclerView: RecyclerView,
//                    viewHolder: RecyclerView.ViewHolder,
//                    target: RecyclerView.ViewHolder
//                ): Boolean {
//                    return false
//                }
//
//                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                    val fCustomer = customerAdapter.currentList[viewHolder.adapterPosition]
//                    viewModel.onCustomerSwiped(fCustomer)
//                }
//
//            }).attachToRecyclerView(recyclerViewCustomer)

            fabAddCustomer.setOnClickListener {
                viewModel.onAddNewCustomerClick()
            }

            /**
             * adapter line
             */
            val dividerItemDecoration = DividerItemDecoration( context, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider))
            binding.recyclerViewCustomer.addItemDecoration(dividerItemDecoration)

        }

        setFragmentResultListener("add_edit_request") { _, bundle ->
            val result = bundle.getInt("add_edit_result")
            viewModel.onAddEditResult(result)
        }


        /**
         * THIS IS MAIN MODEL
         */
        viewModel.fCustomerLive
//                .map {
//                    it.map { newData ->
//
//                        newData
//                    }
//                }
                .observe(viewLifecycleOwner) {
                    customerAdapter.submitList(it)
                }



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.fCustomerEvent.collect { event ->
                when (event) {
                    is CustomerViewModel.CustomerEvent.ShowUndoDeleteCustomerMessage -> {
                        Snackbar.make(requireView(), "Customer deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO") {
                                viewModel.onUndoDeleteClick(event.fCustomer)
                            }.show()
                    }

                    is CustomerViewModel.CustomerEvent.NavigateToAddCustomerScreen -> {
                        val action =
                            CustomerFragmentDirections.actionCustomerFragmentToCustomerFragmentAddEdit(
                                null,
                                "New Customer"
                            )
//                        findNavController().navigate(action)
                    }
                    is CustomerViewModel.CustomerEvent.NavigateToEditCustomerScreen -> {
                        val action =
                                CustomerFragmentDirections.actionCustomerFragmentToCustomerFragmentAddEdit(
                                event.fCustomer,
                                "Edit Customer"
                            )
//                        findNavController().navigate(action)
                    }

                    is CustomerViewModel.CustomerEvent.ShowCustomerSavedConfirmationMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_SHORT).show()
                    }

                    is CustomerViewModel.CustomerEvent.NavigateToDeleteAllCompletedScreen -> {
//                        val action =
//                                CustomerFragmentDirections.actionGlobalDeleteAllCompletedDialogFragment()
//                        findNavController().navigate(action)

                        val alert =
                            AlertDialogWarning(
                                context,
                                "Hapus Seluruh Data?"
                            )
                        alert.getButtonOke().setOnClickListener(View.OnClickListener { view: View? ->
                            alert.dismiss()

                            viewModel.onConfirmDeleteClick()

                        })
                        alert.getButtonCancel()
                            .setOnClickListener(View.OnClickListener {view: View? ->
                                alert.dismiss()
                            })
                        alert.showDialog()


                    }


                    //Kamu Bisa Back Jika Kamu  Menggunakan Navigation ini
//                    is CustomerViewModel.CustomerEvent.NavigateBackWithResult -> {
////                        binding.editTextCustomerName.clearFocus()
////                        setFragmentResult(
////                                "add_edit_request",
////                                bundleOf("add_edit_result" to event.result)
////                        )
//                        findNavController().popBackStack()
//                    }

                    else -> {}
                }.exhaustive
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onItemClick(fCustomer: FCustomer) {
        viewModel.onCustomerSelected(fCustomer)
    }

    override fun onCheckBoxClick(fCustomer: FCustomer, isChecked: Boolean) {
        viewModel.onFCustomerCheckedChanged(fCustomer, isChecked)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_fcustomer, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

        val pendingQuery = viewModel.searchQuery.value
        if (pendingQuery != null && pendingQuery.isNotEmpty()) {
            searchItem.expandActionView()
            searchView.setQuery(pendingQuery, false)
        }

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }

        viewLifecycleOwner.lifecycleScope.launch {
            menu.findItem(R.id.action_hide_inactive).isChecked =
                viewModel.preferencesFlow.first().hideCompleted
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_name -> {
                viewModel.onSortOrderSelected(SortOrder.BY_NAME)
                true
            }
            R.id.action_sort_by_kode -> {
                viewModel.onSortOrderSelected(SortOrder.BY_KODE)
                true
            }
            R.id.action_hide_inactive -> {
                item.isChecked = !item.isChecked
                viewModel.onHideCompletedClick(item.isChecked)
                true
            }
            R.id.action_delete_all_fcustomer -> {
                viewModel.onDeleteAllCompletedClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView.setOnQueryTextListener(null)
    }

}