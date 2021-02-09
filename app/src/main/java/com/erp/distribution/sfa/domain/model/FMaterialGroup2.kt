package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FMaterialGroup2 (
        var id: Int = 0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Int = 0,
        var kode1: String = "",
        var kode2: String = "",
        var description: String = "",

    //	private FMaterialGroup1 fmaterialGroup1Bean;
        var fmaterialGroup1Bean: FMaterialGroup1 = FMaterialGroup1(),
        var isStatusActive: Boolean = true,
        var created: Date = Date(),
        var modified: Date = Date(),
        var modifiedBy: String = "" //User ID

): Parcelable {
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, theDecription: String): this(id = theId, description = theDecription)

}