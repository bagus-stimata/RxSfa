package com.erp.distribution.sfa.presentation.ui.material.material_addedit

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
import com.erp.distribution.sfa.databinding.FragmentAddEditMaterialBinding
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MaterialFragmentAddEdit : Fragment(R.layout.fragment_add_edit_material) {

    private val viewModel: MaterialViewModelAddEdit by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditMaterialBinding.bind(view)

        binding.apply {
            editTextMaterialName.setText(viewModel.fMaterialName)
            checkBoxImportant.isChecked = viewModel.fMaterialImportance
            checkBoxImportant.jumpDrawablesToCurrentState()
            textViewDateCreated.isVisible = viewModel.fMaterial != null
            textViewDateCreated.text = "Created: ${viewModel.fMaterial?.createdDateFormatted}"

            editTextMaterialName.addTextChangedListener {
                viewModel.fMaterialName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModel.fMaterialImportance = isChecked
            }

            fabSaveMaterial.setOnClickListener {
                viewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditMaterialEvent.collect { event ->
                when (event) {
                    is MaterialViewModelAddEdit.AddEditMaterialEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                    is MaterialViewModelAddEdit.AddEditMaterialEvent.NavigateBackWithResult -> {
                        binding.editTextMaterialName.clearFocus()
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