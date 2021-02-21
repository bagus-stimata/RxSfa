package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_addedit

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.databinding.FragmentAddEditSalesorderBinding
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list.FtSaleshAdapter
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@AndroidEntryPoint
class AddEditFtSaleshFragment : Fragment(R.layout.fragment_add_edit_salesorder), FtSalesdItemsAdapter.OnItemClickListener {

    private val viewModel: AddEditFtSaleshViewModel by viewModels()
    private val args: AddEditFtSaleshFragmentArgs by navArgs()

    val sdf = SimpleDateFormat("dd-MMM-yyyy")
    val decFormat = DecimalFormat("#,####")

    lateinit var binding: FragmentAddEditSalesorderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.userViewStateActive?.let {
            viewModel.userViewState =  it
        }
        viewModel.isEditMode =false
        args.ftSalesh?.let {

//            viewModel.ftSalesh = it //Cara ini akan menginvoike pemanggilnya (perequest)
            viewModel.ftSaleshRefno = it.refno
            viewModel.isEditMode = true
        }

//        Toast.makeText(context, "isis ${viewModel.isEditMode}", Toast.LENGTH_LONG).show()

        var ftSalesdItemsAdapter = FtSalesdItemsAdapter(this)

        binding = FragmentAddEditSalesorderBinding.bind(view)
        binding.actionFragment = this

        binding.ftSalesh = viewModel.ftSalesh
        if (viewModel.isEditMode==true){
//            Toast.makeText(context, "isine ${viewModel.isEditMode}", Toast.LENGTH_LONG).show()
            viewModel.getCacheFtSaleshByIdLive(viewModel.ftSaleshRefno).observe(viewLifecycleOwner, Observer {
                it?.let {
//                    Toast.makeText(context, "isine ${it.fcustomerBean.custname}", Toast.LENGTH_LONG).show()
                    viewModel.ftSalesh = it
                    binding.ftSalesh = viewModel.ftSalesh
                }

            })
        }


        requireActivity().onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        viewModel.onPopUpBackStackWithTheResult()
                    }
                })


//      Toast.makeText(context, "date ${viewModel.fieldBinding_InvoiceDate}", Toast.LENGTH_SHORT).show()

        binding.apply {
            recyclerViewFtsaleshFtsalesditems.apply {
                adapter = ftSalesdItemsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }


//            textviewCustname.text = viewModel.fieldBinding_Custname
//            textviewAddress.text = "${viewModel.fieldBinding_Address1} ${viewModel.fieldBinding_Address2} ${viewModel.fieldBinding_City1}"
//
//            textviewOrderno.text = viewModel.fieldBinding_Orderno
//            textviewOrderdate.text = sdf.format( viewModel.fieldBinding_OrderDate )
//
//            textviewInvoiceno.text = viewModel.fieldBinding_Invoiceno
//            textviewInvoicedate.text = sdf.format( viewModel.fieldBinding_InvoiceDate )
//
//            textviewTotalsales.text = decFormat.format(viewModel.fieldBinding_TotalSales)
//            textviewDisc.text = "0"
//            textviewDiscRp.text = "Rp.0"

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

        setupLifeCycleScopeEventListener()
        setupFragmentResultListener()

        setHasOptionsMenu(true)
    }

    fun setupLifeCycleScopeEventListener() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditFtSaleshEvent.collect { event ->
                when (event) {
                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }

                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.NavigateToSelectCustomerScreen -> {
                        val action = AddEditFtSaleshFragmentDirections.actionAddEditFtSaleshFragmentToFCustomerFragment(
                                event.userViewState,
                                event.ftSalesh
                        )
                        findNavController().navigate(action)
                    }
                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.NavigateToSelectMaterialScreen -> {
                        val action = AddEditFtSaleshFragmentDirections.actionAddEditFtSaleshFragmentToFMaterialFragment(
                            event.userViewState,
                            event.ftSalesh,
                            event.ftSalesdItems
                        )
                        findNavController().navigate(action)
                    }
                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.NavigateToSelectFtSalesdItemQtyScreen -> {
                        val action = AddEditFtSaleshFragmentDirections.actionAddEditFtSaleshFragmentToAddEditFtSaleshQtyFragment(
                            event.userViewState,
                            event.ftSalesh,
                            event.ftSalesdItems
                        )
                        findNavController().navigate(action)
                    }
                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.NavigateBackWithResult -> {
//                        binding.editTextSoName.clearFocus()
                        setFragmentResult(
                                "add_edit_request",
                                bundleOf("add_edit_result" to event.ftSalesh)
                        )
                        findNavController().popBackStack()
                    }
                    is AddEditFtSaleshViewModel.AddEditSalesOrderEvent.RenderDataBindingUI -> {
                        //RenderDataBindingUI
                        binding.ftSalesh = viewModel.ftSalesh

                    }

                    else -> {
                        Toast.makeText(context, "Suspend LiveCycle belum di implementasikan", Toast.LENGTH_SHORT).show()
                    }

                }.exhaustive
            }
        }


    }

    fun setupFragmentResultListener() {
        setFragmentResultListener("customer_request") { _, bundle ->
            val result = bundle.getParcelable<FCustomer>("customer_result")
            viewModel.onSelectCustomerResult(result!!)
//            Toast.makeText(context, "Isinya: " + result.custname, Toast.LENGTH_SHORT).show()
        }

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

    override fun onItemClick(ftSalesdItems: FtSalesdItems) {
//        viewModel.onItemSelected(ftSalesdItems)
    }

    fun chooseAndEditCustomer() {
        viewModel.onSelectOrEditCustomer()
    }

    fun chooseAndEditMaterial() {
        viewModel.onSelectOrEditMaterial()
    }
    

}