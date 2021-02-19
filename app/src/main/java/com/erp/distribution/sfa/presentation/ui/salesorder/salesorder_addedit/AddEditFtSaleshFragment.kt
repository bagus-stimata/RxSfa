package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_addedit

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.FragmentAddEditSalesorderBinding
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditFtSaleshFragment : Fragment(R.layout.fragment_add_edit_salesorder) {

    private val viewModel: AddEditFtSaleshViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditSalesorderBinding.bind(view)
        binding.actionFragment = this

        requireActivity().onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        viewModel.onPopUpBackStackWithTheResult()
                    }
                })

        binding.apply {
//            editTextSoName.setText(viewModelFtSalesh.ftSaleshName)
//            checkBoxImportant.isChecked = viewModelFtSalesh.ftSaleshImportance
//            checkBoxImportant.jumpDrawablesToCurrentState()
//            textViewDateCreated.isVisible = viewModelFtSalesh.ftSalesh != null
//            textViewDateCreated.text = "Created: ${viewModelFtSalesh.ftSalesh?.createdDateFormatted}"
//
//            editTextSoName.addTextChangedListener{
//                viewModelFtSalesh.ftSaleshName = it.toString()
//            }
//
//            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
//                viewModelFtSalesh.ftSaleshImportance = isChecked
//            }
//
//            fabSaveSalesorder.setOnClickListener {
//                viewModelFtSalesh.onSaveClick()
//            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditFtSaleshEvent.collect { event ->
                when (event) {
                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }

                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.NavigateToChooseCustomerScreen -> {
                        val action = AddEditFtSaleshFragmentDirections.actionAddEditFtSaleshFragmentToFCustomerFragment()
                        findNavController().navigate(action)
                    }
                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.NavigateToChooseMaterialScreen -> {
                        val action = AddEditFtSaleshFragmentDirections.actionAddEditFtSaleshFragmentToFMaterialFragment()
                        findNavController().navigate(action)
                    }

                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.NavigateBackWithResult -> {
//                        binding.editTextSoName.clearFocus()
                        setFragmentResult(
                            "add_edit_request",
                            bundleOf("add_edit_result" to event.result)
                        )
                        findNavController().popBackStack()
                    }

                    else -> {
                        Toast.makeText(context, "Suspend LiveCycle belum di implementasikan", Toast.LENGTH_SHORT).show()
                    }

                }.exhaustive
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home ->{
                viewModel.onPopUpBackStackWithTheResult()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun chooseAndEditCustomer() {
        viewModel.onChooseOrEditCustomer()
    }

    fun chooseAndEditMaterial() {
        viewModel.onChooseOrEditMaterial()
    }
    

}