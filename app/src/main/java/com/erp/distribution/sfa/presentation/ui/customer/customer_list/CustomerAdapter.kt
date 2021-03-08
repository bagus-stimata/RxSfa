package com.erp.distribution.sfa.presentation.ui.customer.customer_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.databinding.AdapterRvItemFcustomerBinding
import com.erp.distribution.sfa.databinding.AdapterRvItemTemplate3Binding
import com.erp.distribution.sfa.domain.model.FCustomer
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat

class CustomerAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FCustomer, CustomerAdapter.CustomerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding = AdapterRvItemFcustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CustomerViewHolder(private val binding: AdapterRvItemFcustomerBinding) :
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

        fun bind(item: FCustomer) {
            binding.apply {
                nf.maximumFractionDigits = 0
                val hash = item!!.custname.hashCode()
                txtIcon.text = item.custname!!.trim { it <= ' ' }[0].toString()
                txtIcon.background =
                        NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
                txtDate.text = sdf.format(item.modified)

                txtCustname.text = item.custname
                txtCustno.text = item.custno
                txtCustGroup.text = item.fcustomerGroupBean!!.kode1
                txtDivision.text = item.fdivisionBean!!.kode1

                txtAddress.text = "${item.address1} ${item.address2} ${item.city1}"

                txtDate.text = sdf.format(item.modified)
                imgStar.setColorFilter(Color.BLUE)
                imgStar.visibility = View.GONE

            }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: FCustomer)
        fun onCheckBoxClick(item: FCustomer, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<FCustomer>() {
        override fun areItemsTheSame(oldItem: FCustomer, newItem: FCustomer) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FCustomer, newItem: FCustomer) =
            oldItem.custno == newItem.custno
    }
}