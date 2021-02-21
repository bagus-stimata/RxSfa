package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_addedit

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.databinding.AdapterRvItemFmaterialBinding
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat

class FtSalesdItemsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FtSalesdItems, FtSalesdItemsAdapter.FtSalesdItemsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FtSalesdItemsViewHolder {
        val binding = AdapterRvItemFmaterialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FtSalesdItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FtSalesdItemsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class FtSalesdItemsViewHolder(private val binding: AdapterRvItemFmaterialBinding) :
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

//                val hash = item!!.pname.hashCode()
//                if (item.pname.length>1) {
//                    txtIcon.text = item.pname.trim { it <= ' ' }[0].toString()
//                    txtIcon.background =
//                            NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
//                }
//                txtPname.text  = item.pname
//                txtPcode.text = item.pcode
//
////                txtMaterialGroup.text = item.fmaterialGroup3Bean.kode1
//                txtMaterialGroup.text = item.fmaterialGroup3Bean!!.description
//
//
//                txtPrice.text = "Rp ${nf.format(item.spriceAfterPpn)}"
//                txtPrice2.text = "@ ${nf.format(item.sprice2AfterPpn)}"
//
//                txtStock.text = item.minQtyStok.toString()
//
//                imgStar.visibility = View.GONE

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
            oldItem.id == newItem.id
    }
}