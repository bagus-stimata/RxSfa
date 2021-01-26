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
class AddEditMaterialFragment : Fragment(R.layout.fragment_add_edit_material) {

    private val viewModelMaterialViewModel: AddEditMaterialViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditMaterialBinding.bind(view)

        binding.apply {
            editTextMaterialName.setText(viewModelMaterialViewModel.fMaterialName)
            checkBoxImportant.isChecked = viewModelMaterialViewModel.fMaterialImportance
            checkBoxImportant.jumpDrawablesToCurrentState()
            textViewDateCreated.isVisible = viewModelMaterialViewModel.fMaterial != null
            textViewDateCreated.text = "Created: ${viewModelMaterialViewModel.fMaterial?.createdDateFormatted}"

            editTextMaterialName.addTextChangedListener {
                viewModelMaterialViewModel.fMaterialName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModelMaterialViewModel.fMaterialImportance = isChecked
            }

            fabSaveMaterial.setOnClickListener {
                viewModelMaterialViewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelMaterialViewModel.addEditMaterialEvent.collect { event ->
                when (event) {
                    is AddEditMaterialViewModel.AddEditMaterialEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                    is AddEditMaterialViewModel.AddEditMaterialEvent.NavigateBackWithResult -> {
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