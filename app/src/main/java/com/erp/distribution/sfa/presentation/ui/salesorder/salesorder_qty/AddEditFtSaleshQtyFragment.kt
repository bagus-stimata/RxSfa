package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_qty

import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.FragmentAddEditQtySalesorderBinding
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditFtSaleshQtyFragment : Fragment(R.layout.fragment_add_edit_qty_salesorder) {

    private val viewModel: AddEditFtSaleshQtyViewModel by viewModels()
    private val args: AddEditFtSaleshQtyFragmentArgs by navArgs()

    lateinit var binding: FragmentAddEditQtySalesorderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentAddEditQtySalesorderBinding.bind(view)
        binding.actionFragment = this
        binding.ftSalesdItems = viewModel.ftSalesdItems

        args.userViewStateActive?.let {
            viewModel.userViewState =  it
        }
        args.ftSalesh?.let {
            viewModel.ftSalesh = it //Cara ini akan menginvoike pemanggilnya (perequest)
            viewModel.ftSaleshRefno = it.refno
//            Toast.makeText(context, "Hello bos", Toast.LENGTH_SHORT).show()
        }
        args.ftSalesdItems?.let {
            viewModel.ftSalesdItems = it //Cara ini akan menginvoike pemanggilnya (perequest)
            viewModel.ftSalesdItemsId = it.id
            binding.ftSalesdItems = viewModel.ftSalesdItems
//            Toast.makeText(context, "Hello bos : ${viewModel.ftSalesdItems.fmaterialBean.pname}", Toast.LENGTH_SHORT).show()
        }



        binding.numberpickerUom1.apply {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                //Display the newly selected number to text view
                binding.editTextUom1.text = Editable.Factory.getInstance().newEditable(newVal.toString())
            }
        }
        binding.numberpickerUom2.apply {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                //Display the newly selected number to text view
                binding.editTextUom2.text = Editable.Factory.getInstance().newEditable(newVal.toString())
            }
        }
        binding.numberpickerUom3.apply {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                //Display the newly selected number to text view
                binding.editTextUom3.text = Editable.Factory.getInstance().newEditable(newVal.toString())
            }
        }
        binding.numberpickerUom4.apply {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                //Display the newly selected number to text view
                binding.editTextUom4.text = Editable.Factory.getInstance().newEditable(newVal.toString())
            }
        }


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
//            fabSaveSalesorder.setOnClickListener {
//                viewModelFtSalesh.onSaveClick()
//            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditFtSaleshEvent.collect { event ->
                when (event) {
                    is AddEditFtSaleshQtyViewModel.AddEditFtSaleshQtyEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }

                    is AddEditFtSaleshQtyViewModel.AddEditFtSaleshQtyEvent.NavigateToFtSaleshCustomerOrder -> {
                        val action = AddEditFtSaleshQtyFragmentDirections.actionAddEditFtSaleshQtyFragmentToAddEditFtSaleshFragment()
                        findNavController().navigate(action)
                    }

                    is AddEditFtSaleshQtyViewModel.AddEditFtSaleshQtyEvent.NavigateBackWithResult -> {
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
    fun updateQtyOke() {
        viewModel.onUpdateQtyOke()
    }
}