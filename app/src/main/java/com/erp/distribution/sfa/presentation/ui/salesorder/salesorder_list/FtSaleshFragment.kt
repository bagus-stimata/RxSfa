package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list

import android.os.Bundle
import com.erp.distribution.sfa.R
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

@AndroidEntryPoint
class FtSaleshFragment : Fragment(R.layout.fragment_ftsalesh), FtSaleshAdapter.OnItemClickListener {

    private val TAG = FtSaleshFragment::class.java.simpleName

    private val viewModel: FSaleshViewModel by viewModels()
    private val args: FtSaleshFragmentArgs by navArgs()

    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFtsaleshBinding.bind(view)

        args.userViewStateActive?.let {
            viewModel.userViewState =  it
        }

//                Toast.makeText(context, "isinya arguments ${viewModel.userViewState.fUser!!.username}", Toast.LENGTH_SHORT).show()

        val ftSaleshAdapter = FtSaleshAdapter(this)

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                viewModel.popUpBackStackWithTheResult()
            }
        })

        binding.apply {
            recyclerViewFtsalesh.apply {
                adapter = ftSaleshAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }


            /**
             *  Adapter Listener
             */
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
                    viewModel.onItemSwiped(ftSalesh)
                }

            }).attachToRecyclerView(recyclerViewFtsalesh)

            /**
             * Add New
             */
            fabAddFtsalesh.setOnClickListener {
                viewModel.onAddNewFtSaleshClick()
            }

            /**
             * adapter line
             */
            val dividerItemDecoration = DividerItemDecoration( context, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider))
            binding.recyclerViewFtsalesh.addItemDecoration(dividerItemDecoration)

        }


        /**
         * LOADING LIVE DATA TO VIEW
         */
        viewModel.ftSaleshWithItemsLive
            .observe(viewLifecycleOwner) {
                ftSaleshAdapter.submitList(it)

//                var message = ""
//                it.iterator().forEach {
//                    message += "${it.listFtSalesdItems.size}  \n"
//                }
//                Toast.makeText(context, "Isinya:\n ${message}", Toast.LENGTH_SHORT).show()
            }


        setupLifeCycleScopeEventListener()
        setupFragmentResultListener()

        setHasOptionsMenu(true)
    }

    fun setupLifeCycleScopeEventListener() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

            viewModel.ftSaleshEvent.collect { event ->
                when (event) {
                    is FSaleshViewModel.FtSaleshEvent.ShowUndoDeleteFtSaleshMessage -> {
                        Snackbar.make(requireView(), "SalesOrder deleted", Snackbar.LENGTH_LONG)
                                .setAction("UNDO") {
                                    viewModel.onUndoDeleteClick(event.ftSalesh)
                                }.show()
                    }
                    is FSaleshViewModel.FtSaleshEvent.NavigateToAddCustomerOrderScreen -> {
                        val action =
                                FtSaleshFragmentDirections.actionFtSaleshFragmentToAddEditFtSaleshFragment(
                                        event.userViewState
                                )
                        findNavController().navigate(action)
                    }
                    is FSaleshViewModel.FtSaleshEvent.NavigateToEditCustomerOrderScreen -> {
                        val action =
                                FtSaleshFragmentDirections.actionFtSaleshFragmentToAddEditFtSaleshFragment(
                                        event.userViewState,
                                        event.ftSalesh
                                )
                        findNavController().navigate(action)
                    }
                    is FSaleshViewModel.FtSaleshEvent.ShowFtSaleshSavedConfirmationMessage -> {
                        Toast.makeText(requireContext(), event.msg, Toast.LENGTH_SHORT).show()
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

                    else -> {
                        Toast.makeText(requireContext(), "Belum ada Implementasi", Toast.LENGTH_SHORT).show()
                    }
                }.exhaustive
            }
        }

    }
    fun setupFragmentResultListener() {
        setFragmentResultListener("add_edit_request") { _, bundle ->
            try {
                val result = bundle.getParcelable<FtSalesh>("add_edit_result") as FtSalesh
                viewModel.onAddEditResult(result)
            }catch (e:Exception){}
        }

    }

    override fun onItemClick(ftSalesh: FtSalesh) {
        viewModel.onItemSelected(ftSalesh)
    }

    override fun onCheckBoxClick(ftSalesh: FtSalesh, isChecked: Boolean) {
        viewModel.onItemCheckedChanged(ftSalesh, isChecked)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_salesorder, menu)

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

//        viewLifecycleOwner.lifecycleScope.launch {
//            menu.findItem(R.id.action_hide_inactive).isChecked =
//                viewModel.preferencesFlow.first().hideCompleted
//        }

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
//            R.id.action_hide_inactive -> {
//                item.isChecked = !item.isChecked
//                viewModel.onHideCompletedClick(item.isChecked)
//                true
//            }
//            R.id.action_delete_all_completed_tasks -> {
//                viewModel.onDeleteAllCompletedClick()
//                true
//            }
            android.R.id.home -> {
                viewModel.popUpBackStackWithTheResult()
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