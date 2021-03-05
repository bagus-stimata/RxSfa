package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_qty

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.databinding.FragmentAddEditQtySoBinding
import com.erp.distribution.sfa.domain.utils.HeaderDetilSalesHelper
import com.erp.distribution.sfa.domain.utils.HeaderDetilSalesHelperImpl
import com.erp.distribution.sfa.domain.utils.KonversiProductAndStockHelper
import com.erp.distribution.sfa.domain.utils.KonversiProductAndStockHelperImpl
import com.erp.distribution.sfa.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import org.jetbrains.anko.textColor

@AndroidEntryPoint
class AddEditFtSaleshQtyFragment : Fragment(R.layout.fragment_add_edit_qty_so) {

    val TAG = AddEditFtSaleshQtyFragment::class.java.simpleName

    private val viewModel: AddEditFtSaleshQtyViewModel by viewModels()
    private val args: AddEditFtSaleshQtyFragmentArgs by navArgs()

    lateinit var binding: FragmentAddEditQtySoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddEditQtySoBinding.bind(view)
        binding.actionFragment = this
        binding.ftSalesdItems = viewModel.ftSalesdItems

        args.userViewStateActive?.let {
            viewModel.userViewState =  it
//            Toast.makeText(context, "Hello bos ${it.fSalesman?.spname} >> ${it.fWarehouse?.description} ", Toast.LENGTH_SHORT).show()
        }
        args.ftSalesh?.let {
            viewModel.ftSalesh = it //Cara ini akan menginvoike pemanggilnya (perequest)
            viewModel.ftSaleshRefno = it.refno
//            Toast.makeText(context, "Hello bos ${it.fcustomerBean.custname}", Toast.LENGTH_SHORT).show()
        }

        args.ftSalesdItems?.let {


            /**
             * Cek Harga Dengan Prioritas
             * 1. Salesman
             * 2. Customer
             * 3. Group
             */
//            it.sprice = 4000.0


            val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(it.qty, it.fmaterialBean)
            binding.apply {
                val qtyUom1 = kps.getUom1FromSmallest()
                val qtyUom2 = kps.getUom2FromSmallest()
                val qtyUom3 = kps.getUom3FromSmallest()
                val qtyUom4 = kps.getUom4FromSmallest()
                editTextUom1.text = Editable.Factory.getInstance().newEditable(qtyUom1.toInt().toString())
                editTextUom2.text = Editable.Factory.getInstance().newEditable(qtyUom2.toInt().toString())
                editTextUom3.text = Editable.Factory.getInstance().newEditable(qtyUom3.toInt().toString())
                editTextUom4.text = Editable.Factory.getInstance().newEditable(qtyUom4.toInt().toString())

//                viewModel.ftSalesdItems.qty1 = qtyUom1
//                viewModel.ftSalesdItems.qty2 = qtyUom2
//                viewModel.ftSalesdItems.qty3 = qtyUom3
//                viewModel.ftSalesdItems.qty4 = qtyUom4
//                val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
//                viewModel.ftSalesdItems = hds.fillFtSalesd

                val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, it)
                viewModel.ftSalesdItems = hds.fillFtSalesd
                viewModel.ftSalesdItemsId = it.id


                /**
                 * Cek LastStock
                 */

                viewModel.ftSalesdItems.fmaterialBean?.let {
                    viewModel.getStockFromRepoAndUpdateToCache(viewModel.ftSalesdItems.fmaterialBean).observe(viewLifecycleOwner, Observer {
                        it?.let {
                            binding.textviewContent2.text = "KOSONG"
                            binding.textviewContent2.textColor = Color.RED
                            when (it) {
                                else ->{
                                    try {
                                        for (data in it) {
                                            val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(data.saldoAkhir, viewModel.ftSalesdItems.fmaterialBean)
                                            binding.textviewContent2.text = kps.getUom1234StringUom()
                                            binding.textviewContent2.textColor = Color.BLUE
                                        }
                                    }catch (e: java.lang.Exception){}
                                }
//                                checkNotNull(it) ->{
//                                    if (it.saldoAkhir >0) {
//                                        val kps: KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(it.saldoAkhir, viewModel.ftSalesdItems.fmaterialBean)
//                                        binding.textviewContent2.text = kps.getUom1234StringUom()
//                                    }
//                                }

                            }

                        }
                    })
                }




            }


//            viewModel.ftSalesdItems.subtotalAfterDisc2PlusRpAfterPpn = 43000.0
            if ( Math.abs(viewModel.ftSalesdItems.sprice-viewModel.ftSalesdItems.fmaterialBean.sprice) >2) { //pembulatan dari pada menggunakan !=Wh
                viewModel.ftSalesdItems.tempDouble2 = 2.0 //Untuk Warna Harga
            }
            binding.ftSalesdItems = viewModel.ftSalesdItems

//            Toast.makeText(context, "Hello bos : ${viewModel.ftSalesdItems.qty}", Toast.LENGTH_SHORT).show()


            /**
             * Ganti dengan Harga Alternatif Jika ada Harga Alternatif
             */
            var hargaAlternatif = false
            viewModel.userViewState.fSalesman?.let {
                it.ftPriceAlthBean?.let {
//                    Log.d(TAG, "#result >> MASUK 1 ${it}")
                    hargaAlternatif =true
                    viewModel.getHargaAlternatif_FromCacheLive(it).observe(viewLifecycleOwner, Observer {
                        it?.let {
                            if (it.size >0){
                                val hargaAlt = it.get(0).sprice
                                viewModel.ftSalesdItems.tempDouble2 = 2.0 //Untuk Warna Harga
                                viewModel.ftSalesdItems.sprice = hargaAlt
                                val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
                                viewModel.ftSalesdItems = hds.fillFtSalesd
                                binding.ftSalesdItems = viewModel.ftSalesdItems
                            }
                        }
                    })
                }
            }
            if (hargaAlternatif==false) {
                viewModel.ftSalesh.fcustomerBean.ftPriceAlthBean?.let {
//                    Log.d(TAG, "#result >> MASUK 2 ${it}")
                    hargaAlternatif = true
                    viewModel.getHargaAlternatif_FromCacheLive(it).observe(viewLifecycleOwner, Observer {
                        it?.let {
                            if (it.size >0){
                                val hargaAlt = it.get(0).sprice
                                viewModel.ftSalesdItems.tempDouble2 = 2.0 //Untuk Warna Harga
                                viewModel.ftSalesdItems.sprice = hargaAlt
                                val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
                                viewModel.ftSalesdItems = hds.fillFtSalesd
                                binding.ftSalesdItems = viewModel.ftSalesdItems
                            }
                        }
                    })
                }
            }
            if (hargaAlternatif==false) {
                viewModel.ftSalesh.fcustomerBean.fcustomerGroupBean?.let {
                    it.ftPriceAlthBean?.let {
//                        Log.d(TAG, "#result >> MASUK 3 ${it}")
                        viewModel.getFCustomerWithGroup_FromCacheLive(viewModel.ftSalesh.fcustomerBean.id).observe(viewLifecycleOwner, Observer {
                            it.fcustomerGroupBean?.let {
                                if (it.ftPriceAlthBean !=null){
//                                    Log.d(TAG, "#result >> MASUK 4 ${it.ftPriceAlthBean}")
                                    viewModel.getHargaAlternatif_FromCacheLive(it.ftPriceAlthBean!!).observe(viewLifecycleOwner, Observer {
                                        it?.let {
                                            Log.d(TAG, "#result >> MASUK 5 ${it.size}")
                                            if (it.size >0){
                                                val hargaAlt = it.get(0).sprice
                                                viewModel.ftSalesdItems.tempDouble2 = 2.0 //Untuk Warna Harga
                                                Log.d(TAG, "#result >> MASUK 6 ${hargaAlt}")

                                                viewModel.ftSalesdItems.sprice = hargaAlt
                                                val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
                                                viewModel.ftSalesdItems = hds.fillFtSalesd
                                                binding.ftSalesdItems = viewModel.ftSalesdItems
                                            }
                                        }
                                    })

                                }

                            }
                        })


                    }
                }
            }


        }


        binding.numberpickerUom1.apply {
            maxValue = 100
            minValue = 0
            value = viewModel.ftSalesdItems.qty1.toInt()
            setOnValueChangedListener { picker, oldVal, newVal ->
                //Display the newly selected number to text view
                binding.editTextUom1.text = Editable.Factory.getInstance().newEditable(newVal.toString())
            }
        }
        binding.numberpickerUom2.apply {
            maxValue = 100
            minValue = 0
            value = viewModel.ftSalesdItems.qty2.toInt()
            setOnValueChangedListener { picker, oldVal, newVal ->
                //Display the newly selected number to text view
                binding.editTextUom2.text = Editable.Factory.getInstance().newEditable(newVal.toString())
            }
        }
        binding.numberpickerUom3.apply {
            maxValue = 100
            minValue = 0
            value = viewModel.ftSalesdItems.qty3.toInt()
            setOnValueChangedListener { picker, oldVal, newVal ->
                //Display the newly selected number to text view
                binding.editTextUom3.text = Editable.Factory.getInstance().newEditable(newVal.toString())
            }
        }
        binding.numberpickerUom4.apply {
            maxValue = 100
            minValue = 0
            value = viewModel.ftSalesdItems.qty4.toInt()
            setOnValueChangedListener { picker, oldVal, newVal ->
                //Display the newly selected number to text view
                binding.editTextUom4.text = Editable.Factory.getInstance().newEditable(newVal.toString())
            }
        }



        binding.editTextUom1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                try {
//                    viewModel.ftSalesdItems.qty1 = s.toString().toDouble()
                    viewModel.ftSalesdItems.qty = getQtySmallestFromEditText1234()

                    val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
                    viewModel.ftSalesdItems = hds.fillFtSalesd

                    binding.ftSalesdItems = viewModel.ftSalesdItems
                }catch (e: Exception){}
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
//                if (s.length != 0) field1.setText("")
            }
        })
        binding.editTextUom2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                try {
                    viewModel.ftSalesdItems.qty = getQtySmallestFromEditText1234()


//                    Toast.makeText(context, ">>>  ${viewModel.ftSalesdItems.qty} = " +
//                            " ${viewModel.ftSalesdItems.qty1} + ${viewModel.ftSalesdItems.qty2} + ${viewModel.ftSalesdItems.qty3} + ${viewModel.ftSalesdItems.qty4} ", Toast.LENGTH_SHORT).show()
//

                    val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
                    viewModel.ftSalesdItems = hds.fillFtSalesd

                    binding.ftSalesdItems = viewModel.ftSalesdItems
                }catch (e: Exception){}
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
//                if (s.length != 0) field1.setText("")
            }
        })
        binding.editTextUom3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                try {
//                    viewModel.ftSalesdItems.qty3 = s.toString().toDouble()
                    viewModel.ftSalesdItems.qty = getQtySmallestFromEditText1234()

                    val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
                    viewModel.ftSalesdItems = hds.fillFtSalesd

                    binding.ftSalesdItems = viewModel.ftSalesdItems
                }catch (e: Exception){}
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
//                if (s.length != 0) field1.setText("")
            }
        })
        binding.editTextUom4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                try {
//                    viewModel.ftSalesdItems.qty4 = s.toString().toDouble()
                    viewModel.ftSalesdItems.qty = getQtySmallestFromEditText1234()

                    val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
                    viewModel.ftSalesdItems = hds.fillFtSalesd

                    binding.ftSalesdItems = viewModel.ftSalesdItems
                }catch (e: Exception){}
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
//                if (s.length != 0) field1.setText("")
            }
        })


        requireActivity().onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        /**
                         * Fragment Qty Tidak Boleh Back Karena
                         */
//                        viewModel.onPopUpBackStackWithTheResult()
                    }
                })


//        viewModel.hargaAlternatifLive.observe(viewLifecycleOwner, Observer {
//            if (it >0){
//                viewModel.ftSalesdItems.sprice = it
//                val hds: HeaderDetilSalesHelper = HeaderDetilSalesHelperImpl(viewModel.ftSalesh, viewModel.ftSalesdItems)
//                viewModel.ftSalesdItems = hds.fillFtSalesd
//                binding.ftSalesdItems = viewModel.ftSalesdItems
//
//            }
//        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditFtSaleshEvent.collect { event ->
                when (event) {
                    is AddEditFtSaleshQtyViewModel.AddEditFtSaleshQtyEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }

                    is AddEditFtSaleshQtyViewModel.AddEditFtSaleshQtyEvent.NavigateToFtSaleshCustomerOrder -> {
//                        Toast.makeText(context, "Hello Qty ${event.ftSalesdItems.qty.toDouble()}", Toast.LENGTH_SHORT).show()
                        val action = AddEditFtSaleshQtyFragmentDirections.actionAddEditFtSaleshQtyFragmentToAddEditFtSaleshFragment(
                                event.userViewState,
                                event.ftSalesh,
                                event.ftSalesdItems
                        )
                        findNavController().navigate(action)

//                        Toast.makeText(context, "cek ${event.ftSalesh.refno} >> ${event.ftSalesdItems.fmaterialBean.pname}", Toast.LENGTH_SHORT).show()
                    }

                    is AddEditFtSaleshQtyViewModel.AddEditFtSaleshQtyEvent.NavigateBackWithResult -> {
//                        binding.editTextSoName.clearFocus()
//                        setFragmentResult(
//                                "add_edit_request",
//                                bundleOf("add_edit_result" to event.result)
//                        )
//                        findNavController().popBackStack()

                    }

                    else -> {
                        Toast.makeText(context, "Suspend LiveCycle belum di implementasikan", Toast.LENGTH_SHORT).show()
                    }

                }.exhaustive
            }
        }

        setHasOptionsMenu(true)
    }

    fun getQtySmallestFromEditText1234(): Double {
        val qtyUom1: Double = binding.editTextUom1.text.toString().toDouble()
        val qtyUom2: Double = binding.editTextUom2.text.toString().toDouble()
        val qtyUom3: Double = binding.editTextUom3.text.toString().toDouble()
        val qtyUom4: Double = binding.editTextUom4.text.toString().toDouble()

        val kps : KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl()
        val qtySmallestFromUom1234 = kps.getSmallestFromUom1234(viewModel.ftSalesdItems.fmaterialBean, qtyUom1, qtyUom2, qtyUom3, qtyUom4)
        return qtySmallestFromUom1234
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                /**
                 * TIDAK BOLEH BACK
                 */
//                viewModel.onPopUpBackStackWithTheResult()
                updateQtyOke()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
    fun updateQtyOke() {
        viewModel.onUpdateQtySave()
    }

}