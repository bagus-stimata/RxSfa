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
import com.erp.distribution.sfa.databinding.FragmentFmaterialBinding
import com.erp.distribution.sfa.presentation.ui.utils.onQueryTextChanged
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FMaterialFragment : Fragment(R.layout.fragment_fmaterial), FMaterialAdapter.OnItemClickListener {

    private val viewModelFMaterial: FMaterialViewModel by viewModels()

    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFmaterialBinding.bind(view)

        val fMaterialAdapter = FMaterialAdapter(this)


        binding.apply {
            recyclerViewFMaterial.apply {
                adapter = fMaterialAdapter
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
                    val fMaterial = fMaterialAdapter.currentList[viewHolder.adapterPosition]
                    viewModelFMaterial.onItemSwiped(fMaterial)
                }

            }).attachToRecyclerView(recyclerViewFMaterial)



            fabAddFMaterial.setOnClickListener {
                viewModelFMaterial.onAddNewFMaterialClick()
            }


            /**
             * adapter line
             */
            val dividerItemDecoration = DividerItemDecoration( context, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider))
            binding.recyclerViewFMaterial.addItemDecoration(dividerItemDecoration)


        }

        setFragmentResultListener("add_edit_request") { _, bundle ->
            val result = bundle.getInt("add_edit_result")
            viewModelFMaterial.onAddEditResult(result)
        }

        viewModelFMaterial.fMaterialLive.observe(viewLifecycleOwner) {
            fMaterialAdapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelFMaterial.fMaterialEvent.collect { event ->
                when (event) {
                    is FMaterialViewModel.FMaterialEvent.ShowUndoDeleteFMaterialMessage -> {
                        Snackbar.make(requireView(), "Material deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO") {
                                viewModelFMaterial.onUndoDeleteClick(event.fMaterial)
                            }.show()
                    }
                    is FMaterialViewModel.FMaterialEvent.NavigateToAddFMaterialScreen -> {
                        val action =
                            FMaterialFragmentDirections.actionMaterialFragmentToMaterialFragmentAddEdit(
                                null,
                                "New Material"
                            )
                        findNavController().navigate(action)
                    }
                    is FMaterialViewModel.FMaterialEvent.NavigateToEditFMaterialScreen -> {
                        val action =
                            FMaterialFragmentDirections.actionMaterialFragmentToMaterialFragmentAddEdit(
                                event.fMaterial,
                                "Edit Material"
                            )
                        findNavController().navigate(action)
                    }
                    is FMaterialViewModel.FMaterialEvent.ShowFMaterialSavedConfirmationMessage -> {
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
        viewModelFMaterial.onItemSelected(fMaterial)
    }

    override fun onCheckBoxClick(fMaterial: FMaterial, isChecked: Boolean) {
        viewModelFMaterial.onItemCheckedChanged(fMaterial, isChecked)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_simple, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

        val pendingQuery = viewModelFMaterial.searchQuery.value
        if (pendingQuery != null && pendingQuery.isNotEmpty()) {
            searchItem.expandActionView()
            searchView.setQuery(pendingQuery, false)
        }

        searchView.onQueryTextChanged {
            viewModelFMaterial.searchQuery.value = it
        }

        viewLifecycleOwner.lifecycleScope.launch {
            menu.findItem(R.id.action_hide_inactive).isChecked =
                viewModelFMaterial.preferencesFlow.first().hideCompleted
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_name -> {
                viewModelFMaterial.onSortOrderSelected(SortOrder.BY_NAME)
                true
            }
            R.id.action_sort_by_kode -> {
                viewModelFMaterial.onSortOrderSelected(SortOrder.BY_KODE)
                true
            }
            R.id.action_hide_inactive -> {
                item.isChecked = !item.isChecked
                viewModelFMaterial.onHideCompletedClick(item.isChecked)
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