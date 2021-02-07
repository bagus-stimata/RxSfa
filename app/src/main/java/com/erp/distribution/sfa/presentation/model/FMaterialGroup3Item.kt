package com.erp.distribution.sfa.presentation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
class FMaterialGroup3Item (
    var id :Int =0,
    var kode1 :String ="",
    var description :String ="",

    var fmaterialGroup2Bean :Int? =0,
    var isStatusActive :Boolean =false,

    var created : Date = Date(),
    var modified : Date = Date(),
    var modifiedBy :String ="" //User ID

): Model(), Parcelable, Serializable