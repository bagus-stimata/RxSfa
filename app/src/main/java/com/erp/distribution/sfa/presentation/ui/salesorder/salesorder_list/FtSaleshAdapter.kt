package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.data.source.entity.FtSaleshEntity
import com.erp.distribution.sfa.databinding.AdapterRvItemTemplate2Binding
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat

class FtSaleshAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FtSaleshEntity, FtSaleshAdapter.FtSaleshViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FtSaleshViewHolder {
        val binding = AdapterRvItemTemplate2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FtSaleshViewHolder(binding)
    }

    override fun onBindViewHolder(holderF: FtSaleshViewHolder, position: Int) {
        val currentItem = getItem(position)
        holderF.bind(currentItem)
    }

//    inner class TasksViewHolder(private val binding: ItemTaskBinding) :
    inner class FtSaleshViewHolder(private val binding: AdapterRvItemTemplate2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("dd MMM yyyy")
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

        fun bind(item: FtSaleshEntity) {
            binding.apply {
////                checkBoxCompleted.isChecked = item.selected!!
//                textViewName.text = item.invoiceno
////                textViewName.paint.isStrikeThruText = task.completed
////                labelPriority.isVisible = task.important
////                labelPriority.isVisible = item.stared!!


                nf.maximumFractionDigits = 0
                val hash = item!!.invoiceno.hashCode()
//                txtIcon.text = item.invoiceno.trim { it <= ' ' }[0].toString()
                txtIcon.background = NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
                txtCustname.text = "Toko Harapan Jaya Abadi. Tbk"
                txtCustno.text = "FGN12345676"
                txtTipeCust.text = "General Trade"

//                txtAddress.text = "Jl. Kembang Jepun, Gang 5 RT1/RW2, Kecamatan Kedurus, Surabaya"
                txtAddress.text = "Kedurus, Surabaya"
                txtOrderno.text = "ORD.1234567890"
                txtOrderdate.text = "26 Jan 2021"
                txtInvoiceno.text = "INV.1234567890"
                txtInvoicedate.text = "26 Jan 2021"

                txtItemSum.text = "25 items"
                txtCurrency.text = "IDR "
                txtTotal.text = "5.600.000"

                txtDate.text = sdf.format(item.modified)
                imgStar.setColorFilter(Color.BLUE)
            }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: FtSaleshEntity)
        fun onCheckBoxClick(item: FtSaleshEntity, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<FtSaleshEntity>() {
        override fun areItemsTheSame(oldItem: FtSaleshEntity, newItem: FtSaleshEntity) =
            oldItem.refno == newItem.refno

        override fun areContentsTheSame(oldItem: FtSaleshEntity, newItem: FtSaleshEntity) =
            oldItem.orderno == newItem.orderno
    }
}