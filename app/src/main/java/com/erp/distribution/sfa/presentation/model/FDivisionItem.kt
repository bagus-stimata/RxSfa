package com.erp.distribution.sfa.presentation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
class FDivisionItem (
    var id : Int = -1,

    var kode1 : String ="",
    var description : String ="",
    var fcompanyBean : Int =0,
    var isStatusActive : Boolean =false,

    var created: Date? = Date(),
    var modified: Date? = Date(),
    var modifiedBy : String? =""  //User ID
): Parcelable