package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_qty

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
import com.erp.distribution.sfa.databinding.FragmentAddEditQtySalesorderBinding
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditFtSaleshQtyFragment : Fragment(R.layout.fragment_add_edit_qty_salesorder) {

    private val viewModelFtSaleshQty: AddEditFtSaleshQtyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditQtySalesorderBinding.bind(view)

        requireActivity().onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        viewModelFtSaleshQty.popUpBackStackWithTheResult()
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
            viewModelFtSaleshQty.addEditFtSaleshEvent.collect { event ->
                when (event) {
                    is AddEditFtSaleshQtyViewModel.AddEditSalesOrderEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }

                    is AddEditFtSaleshQtyViewModel.AddEditSalesOrderEvent.NavigateBackWithResult -> {
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
                viewModelFtSaleshQty.popUpBackStackWithTheResult()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}