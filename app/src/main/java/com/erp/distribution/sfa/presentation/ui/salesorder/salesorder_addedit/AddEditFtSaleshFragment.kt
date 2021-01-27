package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_addedit

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
import com.erp.distribution.sfa.databinding.FragmentAddEditFmaterialBinding
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditFtSaleshFragment : Fragment(R.layout.fragment_add_edit_fmaterial) {

    private val viewModelFtSaleshViewModel: AddEditFtSaleshViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditFmaterialBinding.bind(view)

        binding.apply {
            editTextName.setText(viewModelFtSaleshViewModel.ftSaleshName)
            checkBoxImportant.isChecked = viewModelFtSaleshViewModel.ftSaleshImportance
            checkBoxImportant.jumpDrawablesToCurrentState()
            textViewDateCreated.isVisible = viewModelFtSaleshViewModel.ftSalesh != null
            textViewDateCreated.text = "Created: ${viewModelFtSaleshViewModel.ftSalesh?.createdDateFormatted}"

            editTextName.addTextChangedListener{
                viewModelFtSaleshViewModel.ftSaleshName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModelFtSaleshViewModel.ftSaleshImportance = isChecked
            }

            fabSaveFmaterial.setOnClickListener {
                viewModelFtSaleshViewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelFtSaleshViewModel.addEditFtSaleshEvent.collect { event ->
                when (event) {
                    is AddEditFtSaleshViewModel.AddEditMaterialEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                    is AddEditFtSaleshViewModel.AddEditMaterialEvent.NavigateBackWithResult -> {
                        binding.editTextName.clearFocus()
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