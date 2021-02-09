package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
class FDivision (
    var id : Int = -1,

    var kode1 : String ="",
    var description : String ="",
    var fcompanyBean : Int =0,
    var isStatusActive : Boolean =false,

    var created: Date? = Date(),
    var modified: Date? = Date(),
    var modifiedBy : String? =""  //User ID
): Parcelable {
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, theDecription: String): this(id = theId, description = theDecription)

}