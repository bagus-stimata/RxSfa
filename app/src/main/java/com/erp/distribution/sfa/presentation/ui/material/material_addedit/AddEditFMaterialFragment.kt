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
class AddEditFMaterialFragment : Fragment(R.layout.fragment_add_edit_fmaterial) {

    private val viewModelFMaterialViewModel: AddEditFMaterialViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditFmaterialBinding.bind(view)

        binding.apply {
            editTextName.setText(viewModelFMaterialViewModel.fMaterialName)
            checkBoxImportant.isChecked = viewModelFMaterialViewModel.fMaterialImportance
            checkBoxImportant.jumpDrawablesToCurrentState()
            textViewDateCreated.isVisible = viewModelFMaterialViewModel.fMaterial != null
            textViewDateCreated.text = "Created: ${viewModelFMaterialViewModel.fMaterial?.createdDateFormatted}"

            editTextName.addTextChangedListener{
                viewModelFMaterialViewModel.fMaterialName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModelFMaterialViewModel.fMaterialImportance = isChecked
            }

            fabSaveFmaterial.setOnClickListener {
                viewModelFMaterialViewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelFMaterialViewModel.addEditFMaterialEvent.collect { event ->
                when (event) {
                    is AddEditFMaterialViewModel.AddEditMaterialEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                    is AddEditFMaterialViewModel.AddEditMaterialEvent.NavigateBackWithResult -> {
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