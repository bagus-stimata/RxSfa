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
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.FragmentAddEditSalesorderBinding
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@AndroidEntryPoint
class AddEditFtSaleshFragment : Fragment(R.layout.fragment_add_edit_salesorder), FtSalesdItemsAdapter.OnItemClickListener {

    private val TAG = AddEditFtSaleshFragment::class.java.simpleName

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
//            viewModel.ftSaleshRefnoLive = it.refno
            viewModel.ftSaleshRefnoLive.postValue(it.refno)
            viewModel.isEditMode = true
        }

        args.ftSalesdItems?.let {
//            viewModel.ftSalesh = it //Cara ini akan menginvoike pemanggilnya (perequest)
//            viewModel.ftSalesh = it.refno
//            viewModel.isEditMode = true
//            Toast.makeText(context, "isis ${it.fmaterialBean.pname} >>> ${it.qty}", Toast.LENGTH_LONG).show()
        }

//        Toast.makeText(context, "isis ${viewModel.isEditMode}", Toast.LENGTH_LONG).show()

        binding = FragmentAddEditSalesorderBinding.bind(view)
        binding.actionFragment = this

        val ftSalesdItemsAdapter = FtSalesdItemsAdapter(this)

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


            /**
             *  Adapter Listener
             */

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    /**
                     * Jika Belum dibaca bisa dihapus
                     */
                    if (viewModel.ftSalesh.unread==true) {
                        val ftSalesdItems = ftSalesdItemsAdapter.currentList[viewHolder.adapterPosition]
                        viewModel.onItemSwiped(ftSalesdItems)
                    }
                }

            }).attachToRecyclerView(recyclerViewFtsaleshFtsalesditems)



            /**
             * adapter line
             */
            val dividerItemDecoration = DividerItemDecoration( context, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider))
            recyclerViewFtsaleshFtsalesditems.addItemDecoration(dividerItemDecoration)
        }

        /**
         * LOADING LIVE DATA TO VIEW
         */

        binding.ftSalesh = viewModel.ftSalesh


        viewModel.ftSaleshRefnoLive.observe(viewLifecycleOwner, Observer {

            viewModel.getCacheFtSaleshByIdLive(it).observe(viewLifecycleOwner, Observer {
                it?.let {
    //                    val tempTotal = viewModel.ftSalesh.amountAfterDiscPlusRpAfterPpn_FG
                    viewModel.ftSalesh = it
    //                    viewModel.ftSalesh.amountAfterDiscPlusRpAfterPpn_FG = tempTotal

                    var totalNota = 0.0

                    for ((key, data) in viewModel.mutableMapFtSalesdItems){
                        if(data.fmaterialBean.convfact1>0) {
                            var totalPrice = (data.qty * data.sprice) / data.fmaterialBean.convfact1 * 1.1
                            totalNota += totalPrice
                            viewModel.ftSalesh = viewModel.ftSalesh.copy(amountAfterDiscPlusRpAfterPpn_FG = totalNota)
                        }
                    }
                    binding.ftSalesh = viewModel.ftSalesh
                }
            })


            viewModel.getCacheFtSalesdItemsByParentLive(it)
                    .observe(viewLifecycleOwner) {
                        when (it) {
                            else -> {
                                viewModel.ftSalesh.amountAfterDiscPlusRpAfterPpn_FG = 0.0 //Karena pada data terachir
                                it?.let {
                                    ftSalesdItemsAdapter.submitList(it)

                                    var totalNota = 0.0
                                    for (data in it){
                                        viewModel.mutableMapFtSalesdItems.put(data.id, data)
                                        if (data.fmaterialBean.convfact1>0) {
                                            var totalPrice = (data.qty * data.sprice) / data.fmaterialBean.convfact1 * 1.1
                                            totalNota += totalPrice
                                            viewModel.ftSalesh = viewModel.ftSalesh.copy(amountAfterDiscPlusRpAfterPpn_FG = totalNota)
                                        }
                                    }
                                }
                                binding.ftSalesh = viewModel.ftSalesh
//                            Log.e(TAG, "#result Detil Dipanggil ${viewModel.ftSalesh.amountAfterDiscPlusRpAfterPpn_FG}")

                            }
                        }
                    }


        })



        setupLifeCycleScopeEventListener()
        setupFragmentResultListener()

        setHasOptionsMenu(true)
    }

    fun setupLifeCycleScopeEventListener() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditFtSaleshEvent.collect { event ->
                when (event) {

                    is AddEditFtSaleshViewModel.AddEditCustomerOrderEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_SHORT).show()
                    }
                    is AddEditFtSaleshViewModel.AddEditCustomerOrderEvent.ShowUndoDeleteFtSaleshMessage -> {
                        Snackbar.make(requireView(), "Item deleted", Snackbar.LENGTH_LONG)
                                .setAction("UNDO") {
                                    viewModel.onUndoDeleteClick(event.ftSalesdItems)
                                }.show()
                    }

                    is AddEditFtSaleshViewModel.AddEditCustomerOrderEvent.NavigateToSelectCustomerScreen -> {
                        val action = AddEditFtSaleshFragmentDirections.actionAddEditFtSaleshFragmentToFCustomerFragment(
                                event.userViewState,
                                event.ftSalesh
                        )
                        findNavController().navigate(action)
                    }
                    is AddEditFtSaleshViewModel.AddEditCustomerOrderEvent.NavigateToSelectMaterialScreen -> {
                        val action = AddEditFtSaleshFragmentDirections.actionAddEditFtSaleshFragmentToFMaterialFragment(
                            event.userViewState,
                            event.ftSalesh
                        )
                        findNavController().navigate(action)
                    }
                    is AddEditFtSaleshViewModel.AddEditCustomerOrderEvent.NavigateToSelectFtSalesdItemQtyScreen -> {
                        val action = AddEditFtSaleshFragmentDirections.actionAddEditFtSaleshFragmentToAddEditFtSaleshQtyFragment(
                            event.userViewState,
                            event.ftSalesh,
                            event.ftSalesdItems
                        )
                        findNavController().navigate(action)
                    }
                    is AddEditFtSaleshViewModel.AddEditCustomerOrderEvent.NavigateBackWithResult -> {
//                        binding.editTextSoName.clearFocus()
                        setFragmentResult(
                                "add_edit_request",
                                bundleOf("add_edit_result" to event.ftSalesh)
                        )
                        findNavController().popBackStack()
                    }
                    is AddEditFtSaleshViewModel.AddEditCustomerOrderEvent.RenderDataBindingUI -> {
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
//            Toast.makeText(context, "Resutl Customer Request: " + result.custname, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home ->{
//                if (viewModel.ftSalesh.stared==true && viewModel.ftSalesh.orderno.toLowerCase().contains("new")){
                if (viewModel.ftSalesh.stared==true  && viewModel.ftSalesh.orderno.toLowerCase().contains("new") ){
                    viewModel.autoSyncToServer()
                }

                viewModel.onPopUpBackStackWithTheResult()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(ftSalesdItems: FtSalesdItems) {
        viewModel.onItemSelected(ftSalesdItems)
    }

    fun chooseAndEditCustomer() {
        viewModel.onSelectOrEditCustomer()
    }

    fun chooseAndEditMaterial() {
        viewModel.onSelectOrEditMaterial()
    }
    

}