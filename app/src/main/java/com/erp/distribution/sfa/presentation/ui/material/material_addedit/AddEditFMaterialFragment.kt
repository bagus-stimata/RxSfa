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
import com.erp.distribution.sfa.databinding.FragmentAddEditFmaterialBinding
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditFMaterialEntityFragment : Fragment(R.layout.fragment_add_edit_fmaterial) {

    private val viewModelFMaterialEntityViewModel: AddEditFMaterialEntityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditFmaterialBinding.bind(view)

        binding.apply {
            editTextName.setText(viewModelFMaterialEntityViewModel.fMaterialName)
            checkBoxImportant.isChecked = viewModelFMaterialEntityViewModel.fMaterialImportance
            checkBoxImportant.jumpDrawablesToCurrentState()
            textViewDateCreated.isVisible = viewModelFMaterialEntityViewModel.fMaterial != null
            textViewDateCreated.text = "Created: ${viewModelFMaterialEntityViewModel.fMaterial?.createdDateFormatted}"

            editTextName.addTextChangedListener{
                viewModelFMaterialEntityViewModel.fMaterialName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModelFMaterialEntityViewModel.fMaterialImportance = isChecked
            }

            fabSaveFmaterial.setOnClickListener {
                viewModelFMaterialEntityViewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelFMaterialEntityViewModel.addEditFMaterialEntityEvent.collect { event ->
                when (event) {
                    is AddEditFMaterialEntityViewModel.AddEditMaterialEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                    is AddEditFMaterialEntityViewModel.AddEditMaterialEvent.NavigateBackWithResult -> {
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