package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.data.source.entity.FtSalesh
import com.erp.distribution.sfa.databinding.AdapterRvItemTemplate1Binding
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat

class FtSaleshAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FtSalesh, FtSaleshAdapter.FtSaleshViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FtSaleshViewHolder {
        val binding = AdapterRvItemTemplate1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FtSaleshViewHolder(binding)
    }

    override fun onBindViewHolder(holderF: FtSaleshViewHolder, position: Int) {
        val currentItem = getItem(position)
        holderF.bind(currentItem)
    }

//    inner class TasksViewHolder(private val binding: ItemTaskBinding) :
    inner class FtSaleshViewHolder(private val binding: AdapterRvItemTemplate1Binding) :
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

        fun bind(item: FtSalesh) {
            binding.apply {
////                checkBoxCompleted.isChecked = item.selected!!
//                textViewName.text = item.invoiceno
////                textViewName.paint.isStrikeThruText = task.completed
////                labelPriority.isVisible = task.important
////                labelPriority.isVisible = item.stared!!


                nf.maximumFractionDigits = 0
                val hash = item!!.invoiceno.hashCode()
                txtIcon.text = item.invoiceno.trim { it <= ' ' }[0].toString()
                txtIcon.background =
                        NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
                txtUser.text = item.invoiceno
                txtSubject.text = item.orderno
                txtPreview.text = ""
                txtDate.text = sdf.format(item.modified)

            }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: FtSalesh)
        fun onCheckBoxClick(item: FtSalesh, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<FtSalesh>() {
        override fun areItemsTheSame(oldItem: FtSalesh, newItem: FtSalesh) =
            oldItem.refno == newItem.refno

        override fun areContentsTheSame(oldItem: FtSalesh, newItem: FtSalesh) =
            oldItem.orderno == newItem.orderno
    }
}