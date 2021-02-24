package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_addedit

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.databinding.AdapterRvItemFtsalesditemsBinding
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.utils.KonversiProductAndStockHelper
import com.erp.distribution.sfa.domain.utils.KonversiProductAndStockHelperImpl
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class FtSalesdItemsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FtSalesdItems, FtSalesdItemsAdapter.FtSalesdItemsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FtSalesdItemsViewHolder {
        val binding = AdapterRvItemFtsalesditemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FtSalesdItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FtSalesdItemsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class FtSalesdItemsViewHolder(private val binding: AdapterRvItemFtsalesditemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("dd-MMM-yyyy")
        val nf = NumberFormat.getInstance()

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onItemClick(task)
                    }
                }

//                checkBoxCompleted.setOnClickListener {
//                    val position = adapterPosition
//                    if (position != RecyclerView.NO_POSITION) {
//                        val task = getItem(position)
//                        listener.onCheckBoxClick(task, checkBoxCompleted.isChecked)
//                    }
//                }



            }
        }

        fun bind(item: FtSalesdItems) {
            binding.apply {
                nf.maximumFractionDigits = 0

                val hash = item.fmaterialBean.pname.hashCode()
                if (item.fmaterialBean.pname.length>1) {
                    txtIcon.text = item.fmaterialBean.pname.trim { it <= ' ' }[0].toString()
                    txtIcon.background =
                            NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
                }
                txtPname.text  = item.fmaterialBean.pname
//                txtPname.text  = "hello bos"

//                txtMaterialGroup.text = item.fmaterialGroup3Bean.kode1
//                txtMaterialGroup.text = item.fmaterialGroup3Bean!!.description

//                txtQty.text = "@ ${nf.format(item.tempString)}"
//                txtPrice.text = "Rp ${nf.format(item.spriceAfterPpn)}"

                val kps : KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl(item.qty, item.fmaterialBean)
                txtQty.text = kps.getUom1234StringUom()
                txtPrice.text = DecimalFormat.getNumberInstance().format(item.sprice)

                var totalPrice = (item.qty * item.sprice) /item.fmaterialBean.convfact1 *1.1
                txtTotal.text = nf.format(totalPrice)

                imgStar.visibility = View.GONE

            }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: FtSalesdItems)
//        fun onCheckBoxClick(item: FtSalesdItems, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<FtSalesdItems>() {
        override fun areItemsTheSame(oldItem: FtSalesdItems, newItem: FtSalesdItems) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FtSalesdItems, newItem: FtSalesdItems) =
            oldItem.id == newItem.id && oldItem.fmaterialBean.id==newItem.fmaterialBean.id && oldItem.ftSaleshBean.refno==newItem.ftSaleshBean.refno
    }
}