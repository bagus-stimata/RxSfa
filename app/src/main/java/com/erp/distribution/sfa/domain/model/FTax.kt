package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FTaxEntity
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
data class FTax (
    var id :Int = 0,
    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID :Int = 0,
    var kode1 :String = "",
    var kode2 :String = "",
    var description :String = "",
    var taxPercent :Double =0.0,

    //	private AccAccount accAccountTaxPurchaseBean;
    var accAccountTaxPurchaseBean :Int = 0,

    //	private AccAccount accAccountTaxSalesBean;
    var accAccountTaxSalesBean :Int = 0,

    //	private FDivision fdivisionBean;
    var fdivisionBean :FDivision = FDivision(),

    var isStatusActive :Boolean = true,
    var created : Date = Date(),
    var modified : Date = Date(),
    var modifiedBy :String = ""//User ID
):Parcelable



