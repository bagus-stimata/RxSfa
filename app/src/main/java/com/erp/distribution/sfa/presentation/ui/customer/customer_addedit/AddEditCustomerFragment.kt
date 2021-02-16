package com.erp.distribution.sfa.presentation.ui.customer.customer_addedit

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.FragmentAddEditCustomerBinding
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditCustomerFragment : Fragment(R.layout.fragment_add_edit_customer) {

    private val viewModelCustomerViewModel: AddEditCustomerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: FragmentAddEditCustomerBinding = FragmentAddEditCustomerBinding.bind(view)

        binding.apply {
            editTextCustomerName.setText(viewModelCustomerViewModel.fCustomerName)
            checkBoxImportant.isChecked = viewModelCustomerViewModel.fCustomerImportance
            checkBoxImportant.jumpDrawablesToCurrentState()
            textViewDateCreated.isVisible = viewModelCustomerViewModel.fCustomer != null
            textViewDateCreated.text = "Created: ${viewModelCustomerViewModel.fCustomer?.createdDateFormatted}"

            editTextCustomerName.addTextChangedListener {
                viewModelCustomerViewModel.fCustomerName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModelCustomerViewModel.fCustomerImportance = isChecked
            }

            fabSaveCustomer.setOnClickListener {
                viewModelCustomerViewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelCustomerViewModel.addEditCustomerEventFlow.collect { event ->
                when (event) {
                    is AddEditCustomerViewModel.AddEditCustomerEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                    is AddEditCustomerViewModel.AddEditCustomerEvent.NavigateBackWithResult -> {
                        binding.editTextCustomerName.clearFocus()
                        setFragmentResult(
                            "add_edit_request",
                            bundleOf("add_edit_result" to event.result)
                        )
                        findNavController().popBackStack()
                    }
                }.exhaustive
            }
        }


    }

}