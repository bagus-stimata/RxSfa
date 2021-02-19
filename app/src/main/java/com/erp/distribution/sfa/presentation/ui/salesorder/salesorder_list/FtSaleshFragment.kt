package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list

import android.os.Bundle
import com.erp.distribution.sfa.R
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.databinding.FragmentFtsaleshBinding
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.presentation.ui.utils.onQueryTextChanged
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FtSaleshFragment : Fragment(R.layout.fragment_ftsalesh), FtSaleshAdapter.OnItemClickListener {

    private val TAG = FtSaleshFragment::class.java.simpleName

    private val viewModelFSalesh: FSaleshViewModel by viewModels()

    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFtsaleshBinding.bind(view)

        val ftSaleshAdapter = FtSaleshAdapter(this)

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
//                myPopBackStack()
                viewModelFSalesh.popUpBackStackWithTheResult()
            }
        })

        binding.apply {
            recyclerViewFtsalesh.apply {
                adapter = ftSaleshAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val ftSalesh = ftSaleshAdapter.currentList[viewHolder.adapterPosition]
                    viewModelFSalesh.onItemSwiped(ftSalesh)
                }

            }).attachToRecyclerView(recyclerViewFtsalesh)



            fabAddFtsalesh.setOnClickListener {
                viewModelFSalesh.onAddNewFtSaleshClick()
            }

            /**
             * adapter line
             */
            val dividerItemDecoration = DividerItemDecoration( context, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider))
            binding.recyclerViewFtsalesh.addItemDecoration(dividerItemDecoration)


        }

        setFragmentResultListener("add_edit_request") { _, bundle ->
            val result = bundle.getInt("add_edit_result")
            viewModelFSalesh.onAddEditResult(result)
        }

//        viewModelFSalesh.ftSaleshLive
//            .observe(viewLifecycleOwner) {
//                ftSaleshAdapter.submitList(it)
//        }
        /**
         * THIS IS MAIN MODEL
         */
        viewModelFSalesh.ftSaleshLive
            .observe(viewLifecycleOwner) {
                ftSaleshAdapter.submitList(it)
            }



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            viewModelFSalesh.ftSaleshEvent.collect { event ->
                when (event) {
                    is FSaleshViewModel.FtSaleshEvent.ShowUndoDeleteFtSaleshMessage -> {
                        Snackbar.make(requireView(), "SalesOrder deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO") {
                                viewModelFSalesh.onUndoDeleteClick(event.ftSalesh)
                            }.show()
                    }
                    is FSaleshViewModel.FtSaleshEvent.NavigateToAddSalesOrderScreen -> {
                        val action =
                            FtSaleshFragmentDirections.actionFtSaleshFragmentToAddEditFtSaleshFragment(
                                null
                            )
                        findNavController().navigate(action)
                    }
                    is FSaleshViewModel.FtSaleshEvent.NavigateToEditSalesOrderScreen -> {
                        val action =
                            FtSaleshFragmentDirections.actionFtSaleshFragmentToAddEditFtSaleshFragment(
                                    event.userViewState,
                                    event.ftSalesh
                            )
                        findNavController().navigate(action)
                    }
                    is FSaleshViewModel.FtSaleshEvent.ShowFtSaleshSavedConfirmationMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_SHORT).show()
                    }

                    is FSaleshViewModel.FtSaleshEvent.NavigateBackWithResult -> {
//                        binding.editTextSoName.clearFocus()
                        setFragmentResult(
                                "request_id",
                                bundleOf("result_id" to event.result)
                        )
                        findNavController().popBackStack()
                    }


                    else -> {}
                }.exhaustive
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onItemClick(ftSalesh: FtSalesh) {
        viewModelFSalesh.onItemSelected(ftSalesh)
    }

    override fun onCheckBoxClick(ftSalesh: FtSalesh, isChecked: Boolean) {
        viewModelFSalesh.onItemCheckedChanged(ftSalesh, isChecked)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_simple, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

        val pendingQuery = viewModelFSalesh.searchQuery.value
        if (pendingQuery != null && pendingQuery.isNotEmpty()) {
            searchItem.expandActionView()
            searchView.setQuery(pendingQuery, false)
        }

        searchView.onQueryTextChanged {
            viewModelFSalesh.searchQuery.value = it
        }

        viewLifecycleOwner.lifecycleScope.launch {
            menu.findItem(R.id.action_hide_inactive).isChecked =
                viewModelFSalesh.preferencesFlow.first().hideCompleted
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_name -> {
                viewModelFSalesh.onSortOrderSelected(SortOrder.BY_NAME)
                true
            }
            R.id.action_sort_by_kode -> {
                viewModelFSalesh.onSortOrderSelected(SortOrder.BY_KODE)
                true
            }
            R.id.action_hide_inactive -> {
                item.isChecked = !item.isChecked
                viewModelFSalesh.onHideCompletedClick(item.isChecked)
                true
            }
//            R.id.action_delete_all_completed_tasks -> {
//                viewModel.onDeleteAllCompletedClick()
//                true
//            }
            android.R.id.home -> {
                viewModelFSalesh.popUpBackStackWithTheResult()
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