package com.erp.distribution.sfa.presentation.ui.customer.customer_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.databinding.AdapterRvItemTemplate3Binding
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat

class CustomerAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FCustomerEntity, CustomerAdapter.CustomerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding = AdapterRvItemTemplate3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CustomerViewHolder(private val binding: AdapterRvItemTemplate3Binding) :
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

        fun bind(item: FCustomerEntity) {
            binding.apply {
////                checkBoxCompleted.isChecked = item.selected!!
//                textViewName.text = item.pname
////                textViewName.paint.isStrikeThruText = task.completed
////                labelPriority.isVisible = task.important
////                labelPriority.isVisible = item.stared!!


                nf.maximumFractionDigits = 0
                val hash = item!!.custname.hashCode()
                txtIcon.text = item.custname.trim { it <= ' ' }[0].toString()
                txtIcon.background =
                        NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
                txtUser.text = item.custname
                txtSubject.text = item.custno + " General Trade"
                txtPreview.text = "${item.address1} ${item.address2} ${item.address3} " +
                        "${item.city1}"

                txtDate.text = sdf.format(item.modified)

            }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: FCustomerEntity)
        fun onCheckBoxClick(item: FCustomerEntity, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<FCustomerEntity>() {
        override fun areItemsTheSame(oldItem: FCustomerEntity, newItem: FCustomerEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FCustomerEntity, newItem: FCustomerEntity) =
            oldItem.custno == newItem.custno
    }
}