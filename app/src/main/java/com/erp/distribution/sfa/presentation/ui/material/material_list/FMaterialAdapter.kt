package com.erp.distribution.sfa.presentation.ui.material.material_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.databinding.AdapterRvItemFmaterialBinding
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat

class FMaterialAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FMaterial, FMaterialAdapter.FMaterialViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FMaterialViewHolder {
        val binding = AdapterRvItemFmaterialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FMaterialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FMaterialViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class FMaterialViewHolder(private val binding: AdapterRvItemFmaterialBinding) :
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

        fun bind(item: FMaterial) {
            binding.apply {

                nf.maximumFractionDigits = 0

                val hash = item!!.pname.hashCode()
                txtIcon.text = item.pname.trim { it <= ' ' }[0].toString()
                txtIcon.background =
                        NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)

                txtPname.text  = item.pname
                txtPcode.text = item.pcode
                txtMaterialGroup.text = item.fmaterialGroup3Bean.toString()
                txtPrice.text = "Rp ${nf.format(item.spriceAfterPpn)}"
                txtPrice2.text = "@ ${nf.format(item.sprice2AfterPpn)}"

                txtStock.text = item.minQtyStok.toString()



                imgStar.visibility = View.GONE
            }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: FMaterial)
        fun onCheckBoxClick(item: FMaterial, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<FMaterial>() {
        override fun areItemsTheSame(oldItem: FMaterial, newItem: FMaterial) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FMaterial, newItem: FMaterial) =
            oldItem.pcode == newItem.pcode
    }
}