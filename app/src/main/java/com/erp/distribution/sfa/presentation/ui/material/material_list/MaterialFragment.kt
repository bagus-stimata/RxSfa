package com.erp.distribution.sfa.presentation.ui.material.material_list

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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.FMaterial
import com.erp.distribution.sfa.databinding.FragmentMaterialBinding
import com.erp.distribution.sfa.presentation.ui.utils.onQueryTextChanged
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MaterialFragment : Fragment(R.layout.fragment_material), MaterialAdapter.OnItemClickListener {

    private val viewModel: MaterialViewModel by viewModels()

    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMaterialBinding.bind(view)

        val materialAdapter = MaterialAdapter(this)

        binding.apply {
            recyclerViewMaterial.apply {
                adapter = materialAdapter
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
                    val fMaterial = materialAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.onMaterialSwiped(fMaterial)
                }

            }).attachToRecyclerView(recyclerViewMaterial)



            fabAddMaterial.setOnClickListener {
                viewModel.onAddNewMaterialClick()
            }


            /**
             * adapter line
             */
            val dividerItemDecoration = DividerItemDecoration( context, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider))
            binding.recyclerViewMaterial.addItemDecoration(dividerItemDecoration)


        }

        setFragmentResultListener("add_edit_request") { _, bundle ->
            val result = bundle.getInt("add_edit_result")
            viewModel.onAddEditResult(result)
        }

        viewModel.fMaterialLive.observe(viewLifecycleOwner) {
            materialAdapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.fMaterialEvent.collect { event ->
                when (event) {
                    is MaterialViewModel.MaterialEvent.ShowUndoDeleteMaterialMessage -> {
                        Snackbar.make(requireView(), "Material deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO") {
                                viewModel.onUndoDeleteClick(event.fMaterial)
                            }.show()
                    }
                    is MaterialViewModel.MaterialEvent.NavigateToAddMaterialScreen -> {
                        val action =
                            MaterialFragmentDirections.actionMaterialFragmentToMaterialFragmentAddEdit(
                                null,
                                "New Material"
                            )
                        findNavController().navigate(action)
                    }
                    is MaterialViewModel.MaterialEvent.NavigateToEditMaterialScreen -> {
                        val action =
                                MaterialFragmentDirections.actionMaterialFragmentToMaterialFragmentAddEdit(
                                event.fMaterial,
                                "Edit Material"
                            )
                        findNavController().navigate(action)
                    }
                    is MaterialViewModel.MaterialEvent.ShowMaterialSavedConfirmationMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_SHORT).show()
                    }

//                    is MaterialViewModel.MaterialEvent.NavigateToDeleteAllCompletedScreen -> {
//                        val action =
//                                MaterialFragmentDirections.actionGlobalDeleteAllCompletedDialogFragment()
//                        findNavController().navigate(action)
//                    }

                    else -> {}
                }.exhaustive
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onItemClick(fMaterial: FMaterial) {
        viewModel.onMaterialSelected(fMaterial)
    }

    override fun onCheckBoxClick(fMaterial: FMaterial, isChecked: Boolean) {
        viewModel.onFMaterialCheckedChanged(fMaterial, isChecked)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_simple, menu)

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
            menu.findItem(R.id.action_hide_inactive_material).isChecked =
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
            R.id.action_hide_inactive_material -> {
                item.isChecked = !item.isChecked
                viewModel.onHideCompletedClick(item.isChecked)
                true
            }
//            R.id.action_delete_all_completed_tasks -> {
//                viewModel.onDeleteAllCompletedClick()
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView.setOnQueryTextListener(null)
    }

}