package com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.databinding.AdapterRvItemTemplate1Binding
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import java.text.NumberFormat
import java.text.SimpleDateFormat

/**
 * Created by wisnu on 8/7/18
 */
class NoteAdapter(private val itemLongClickListener: (Int) -> Unit) : RecyclerView.Adapter<NoteViewHolder>() {

    private var data: MutableList<FMaterialEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        val itemView = LayoutInflater
//                .from(parent.context)
//                .inflate(R.layout.item_note, parent, false)
//        return NoteViewHolder(itemView, itemLongClickListener)

        val binding: AdapterRvItemTemplate1Binding = AdapterRvItemTemplate1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, itemLongClickListener)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bindData(data[position])
        }
    }
    override fun getItemCount(): Int = data.size

    fun getDataAtPosition(position: Int): FMaterialEntity = data[position]

    fun setData(data: MutableList<FMaterialEntity>) {
        val diffResult = DiffUtil.calculateDiff(
            NoteDiffUtilCallback(this.data, data)
        )

        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateData(data: MutableList<FMaterialEntity>) {
        val diffResult = DiffUtil.calculateDiff(
            NoteDiffUtilCallback(this.data, data)
        )

        this.data.clear()
        this.data.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FMaterialEntity> =
            object : DiffUtil.ItemCallback<FMaterialEntity>() {
                override fun areItemsTheSame(oldItem: FMaterialEntity, newItem: FMaterialEntity): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: FMaterialEntity, newItem: FMaterialEntity): Boolean {
                    return oldItem.pcode == newItem.pcode && oldItem.pname == newItem.pname
                }
            }

        fun oval(@ColorInt color: Int, view: View): ShapeDrawable {
            val shapeDrawable = ShapeDrawable(OvalShape())
            shapeDrawable.intrinsicHeight = view.height
            shapeDrawable.intrinsicWidth = view.width
            shapeDrawable.paint.color = color
            return shapeDrawable
        }
    }

    init {
//        nf.maximumFractionDigits = 0
    }

}

class NoteViewHolder(private val itemBinding: AdapterRvItemTemplate1Binding,
                     itemLongClickListener: (Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {
    val sdf = SimpleDateFormat("dd MMM yyyy")
    val nf = NumberFormat.getInstance()

    init {
        itemView.setOnLongClickListener {
            itemLongClickListener(adapterPosition)
            return@setOnLongClickListener true
        }
    }
    private lateinit var fMaterialEntityBean: FMaterialEntity
    fun bindData(item: FMaterialEntity) {


        nf.maximumFractionDigits = 0

        this.fMaterialEntityBean = item


        val hash = item!!.pname.hashCode()
        itemBinding.txtIcon.text = item.pname.trim { it <= ' ' }[0].toString()
        itemBinding.txtIcon.background =
            NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), itemBinding.txtIcon)
        itemBinding.txtUser.text = item.pname
        itemBinding.txtSubject.text = item.pcode
        itemBinding.txtPreview.text = "IDR " + nf.format(item.spriceAfterPpn) + " @" + nf.format(
            item.sprice2AfterPpn
        )
        itemBinding.txtDate.text = sdf.format(item.modified)

    }


    private fun oval(@ColorInt color: Int, view: View): ShapeDrawable {
        val shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.intrinsicHeight = view.height
        shapeDrawable.intrinsicWidth = view.width
        shapeDrawable.paint.color = color
        return shapeDrawable
    }

}