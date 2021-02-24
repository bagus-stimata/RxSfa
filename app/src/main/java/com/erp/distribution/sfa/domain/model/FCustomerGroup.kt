package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class FCustomerGroup (
    var id : Int = 0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var kode1 : String = "",
    var description: String = "",
    var isStatusActive : Boolean = false,

    var sourceId : Int? = 0,

    //	private FDivision fdivisionBean;
//    var fdivisionBean : Int? = 0,
    var fdivisionBean: FDivision? = FDivision(),

    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean : Int? = 0,

    var created : Date? = Date(),
    var modified : Date? = Date(),
    var modifiedBy : String? = "" //User ID
): Parcelable {
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, theDecription: String): this(id = theId, description = theDecription)

}