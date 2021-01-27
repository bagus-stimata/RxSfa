package com.erp.distribution.sfa.presentation.ui.test.mvvm_todo.tasks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.databinding.AdapterRvItemTemplate1Binding
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat

class TasksAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FMaterialEntity, TasksAdapter.TasksViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
//        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val binding = AdapterRvItemTemplate1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

//    inner class TasksViewHolder(private val binding: ItemTaskBinding) :
    inner class TasksViewHolder(private val binding: AdapterRvItemTemplate1Binding) :
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

        fun bind(item: FMaterialEntity) {
            binding.apply {
////                checkBoxCompleted.isChecked = item.selected!!
//                textViewName.text = item.pname
////                textViewName.paint.isStrikeThruText = task.completed
////                labelPriority.isVisible = task.important
////                labelPriority.isVisible = item.stared!!


                nf.maximumFractionDigits = 0
                val hash = item!!.pname.hashCode()
                txtIcon.text = item.pname.trim { it <= ' ' }[0].toString()
                txtIcon.background =
                        NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
                txtUser.text = item.pname
                txtSubject.text = item.pcode
                txtPreview.text = "IDR " + nf.format(item.spriceAfterPpn) + " @" + nf.format(
                        item.sprice2AfterPpn
                )
                txtDate.text = sdf.format(item.modified)

            }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: FMaterialEntity)
        fun onCheckBoxClick(task: FMaterialEntity, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<FMaterialEntity>() {
        override fun areItemsTheSame(oldItem: FMaterialEntity, newItem: FMaterialEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FMaterialEntity, newItem: FMaterialEntity) =
            oldItem.pcode == newItem.pcode
    }
}