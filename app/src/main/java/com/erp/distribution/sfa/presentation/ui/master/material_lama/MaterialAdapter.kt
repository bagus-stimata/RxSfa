package com.erp.distribution.sfa.presentation.ui.master.material_lama

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.presentation.ui.master.material_lama.MaterialAdapter.FMaterialHolder
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import java.text.NumberFormat
import java.text.SimpleDateFormat

class MaterialAdapter : ListAdapter<FMaterialEntity, FMaterialHolder>(DIFF_CALLBACK) {
    private var listener: OnItemClickListener? = null
    var sdf = SimpleDateFormat("dd MMM yyyy")
    var nf = NumberFormat.getInstance()
    val selectedItems = SparseBooleanArray()
    private val currentSelectedPos = 0
    fun getFMaterialAt(position: Int): FMaterialEntity? {
        return getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FMaterialHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_rv_item_template1, parent, false)
        return FMaterialHolder(itemView)
    }

    override fun onBindViewHolder(holder: FMaterialHolder, position: Int) {
        val currentNote = getItem(position)
        holder.bind(currentNote)
    }

    inner class FMaterialHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtUser: TextView
        var txtIcon: TextView
        var txtSubject: TextView
        var txtPreview: TextView
        var txtDate: TextView
        var imgStar: ImageView
        fun bind(fMaterialEntity: FMaterialEntity?) {
            val hash = fMaterialEntity!!.pname.hashCode()
            txtIcon.text = fMaterialEntity.pname.trim { it <= ' ' }[0].toString()
            txtIcon.background =
                oval(Color.rgb(hash, hash / 2, 0), txtIcon)
            txtUser.text = fMaterialEntity.pname
            txtSubject.text = fMaterialEntity.pcode
            txtPreview.text = "IDR ${nf.format(fMaterialEntity.spriceAfterPpn)} @ ${nf.format(fMaterialEntity.sprice2AfterPpn)}"

            txtDate.text = sdf.format(fMaterialEntity.modified)



//            txtUser.setTypeface(
//                Typeface.DEFAULT,
//                if (fMaterial.isSelected) Typeface.BOLD else Typeface.NORMAL
//            )
//            txtSubject.setTypeface(
//                Typeface.DEFAULT,
//                if (fMaterial.isSelected) Typeface.BOLD else Typeface.NORMAL
//            )
//            txtDate.setTypeface(
//                Typeface.DEFAULT,
//                if (fMaterial.isSelected) Typeface.BOLD else Typeface.NORMAL
//            )
//            imgStar.setImageResource(if (fMaterial.isStared) R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp)
//            if (fMaterial.isSelected) {
//                txtIcon.background =
//                    oval(Color.rgb(26, 115, 233), txtIcon)
//                val gradientDrawable = GradientDrawable()
//                gradientDrawable.shape = GradientDrawable.RECTANGLE
//                gradientDrawable.cornerRadius = 32f
//                gradientDrawable.setColor(Color.rgb(232, 240, 253))
//                itemView.background = gradientDrawable
//            } else {
//                val gradientDrawable = GradientDrawable()
//                gradientDrawable.shape = GradientDrawable.RECTANGLE
//                gradientDrawable.cornerRadius = 32f
//                gradientDrawable.setColor(Color.WHITE)
//                itemView.background = gradientDrawable
//            }



            // animation
            if (selectedItems.size() > 0) animate(txtIcon, fMaterialEntity)
        }

        private fun animate(view: TextView, fMaterialEntity: FMaterialEntity?) {
            val oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f)
            val oa2 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
            oa1.interpolator = DecelerateInterpolator()
            oa2.interpolator = AccelerateDecelerateInterpolator()
            oa1.duration = 200
            oa2.duration = 200
            oa1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
//                    if (fMaterial!!.isSelected) view.text = "\u2713"
//                    oa2.start()
                }
            })
            oa1.start()
        }

        init {
            txtUser = itemView.findViewById(R.id.txt_pname)
            txtIcon = itemView.findViewById(R.id.txt_icon)
            txtSubject = itemView.findViewById(R.id.txt_subject)
            txtPreview = itemView.findViewById(R.id.txt_preview)
            txtDate = itemView.findViewById(R.id.txt_date)
            imgStar = itemView.findViewById(R.id.img_star)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(getItem(position))
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(note: FMaterialEntity?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FMaterialEntity> =
            object : DiffUtil.ItemCallback<FMaterialEntity>() {
                override fun areItemsTheSame(oldItem: FMaterialEntity, newItem: FMaterialEntity): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: FMaterialEntity, newItem: FMaterialEntity): Boolean {
                    return oldItem.pcode == newItem.pcode && oldItem.pname == newItem.pname
                }
            }

        private fun oval(@ColorInt color: Int, view: View): ShapeDrawable {
            val shapeDrawable = ShapeDrawable(OvalShape())
            shapeDrawable.intrinsicHeight = view.height
            shapeDrawable.intrinsicWidth = view.width
            shapeDrawable.paint.color = color
            return shapeDrawable
        }
    }

    init {
        nf.maximumFractionDigits = 0
    }
}