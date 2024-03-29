package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.databinding.AdapterRvItemTemplate2Binding
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.usecase.GetFCustomerUseCase
import com.erp.distribution.sfa.presentation.ui.master.material_lama.adapter.NoteAdapter
import org.jetbrains.anko.Android
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FtSaleshAdapter(private val listener: OnItemClickListener) :
    ListAdapter<FtSalesh, FtSaleshAdapter.FtSaleshViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FtSaleshViewHolder {
        val binding = AdapterRvItemTemplate2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FtSaleshViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FtSaleshViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
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
                        val newItem = getItem(position)
                        listener.onItemClick(newItem)
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
                val hash = item!!.fcustomerBean.custname.hashCode()
                if (item.fcustomerBean.custname.length >1) {
                    txtIcon.text = item.fcustomerBean.custname.trim { it <= ' ' }[0].toString()
                    txtIcon.background = NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
                }

//                val hash = item.fmaterialBean.pname.hashCode()
//                if (item.fmaterialBean.pname.length>1) {
//                    txtIcon.text = item.fmaterialBean.pname.trim { it <= ' ' }[0].toString()
//                    txtIcon.background =
//                            NoteAdapter.oval(Color.rgb(hash, hash / 2, 0), binding.txtIcon)
//                }


                txtCustname.text = item.fcustomerBean.custname

                txtCustno.text = item.fcustomerBean.custno
                txtTipeCust.text = item.fcustomerBean.fcustomerGroupBean!!.kode1

                txtAddress.text = "${item.fcustomerBean.address1} ${item.fcustomerBean.address2} ${item.fcustomerBean.city1} "
                if (txtAddress.text.trim().equals("")){
                    txtAddress.visibility = View.GONE
                }else {
                    txtAddress.visibility = View.VISIBLE
                }

                txtOrderno.text = item.orderno
                txtOrderdate.text = sdf.format(item.orderDate)
                txtInvoiceno.text = item.invoiceno
                txtInvoicedate.text = sdf.format(item.invoiceDate)

//                var counter = 0
//                var total = 0.0
//                for (data in item.listFtSalesdItems){
//                    var totalPrice = (data.qty * data.sprice)*1.1
//                    total += totalPrice
//                    ++counter
//                }
                if (item.tempInt1>0) txtItemSum.text = "${item.tempInt1} items"

                txtTotal.text = "${nf.format(item.amountAfterDiscPlusRpAfterPpn_FG)}"

                if (item.amountAfterDiscPlusRpAfterPpn_FG==0.0){
                    txtItemSum.textColor = Color.LTGRAY
                    txtTotal.textColor = Color.LTGRAY
                }else {
                    txtItemSum.textColor = Color.BLUE
                    txtTotal.textColor = Color.BLUE
                }

                txtDate.text = sdf.format(item.modified)

                /**
                 * Star -> Sudah Di Upload
                 */
                if (item.stared==true && item.unread==false){
                    imgStar.setColorFilter(Color.BLUE)
                }else if (item.stared==true){
                    imgStar.setColorFilter(Color.GREEN)
                }else {
                    imgStar.setColorFilter(Color.GRAY)
                }

                if (item.callPlan) {
                    val gradientDrawable = GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM,
                            intArrayOf(Color.parseColor("#e0ffe0"),
                                    Color.parseColor("#fafcf7"))
                    );
                    gradientDrawable.cornerRadius = 0f;

//                Set Gradient
                    relativeLayout.setBackground(gradientDrawable);
                }else {
                    relativeLayout.background = null;
                }

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
            oldItem.fcustomerBean == newItem.fcustomerBean &&
                    oldItem.orderno ==newItem.orderno &&
                    oldItem.invoiceno ==newItem.invoiceno

    }
}