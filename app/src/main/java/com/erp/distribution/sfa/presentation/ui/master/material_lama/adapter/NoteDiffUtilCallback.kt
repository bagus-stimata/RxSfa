package com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter

import androidx.recyclerview.widget.DiffUtil
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity

/**
 * Created by wisnu on 8/7/18
 */
class NoteDiffUtilCallback(private val oldData: MutableList<FMaterialEntity>,
                           private val newData: MutableList<FMaterialEntity>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int,
                                 newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}