package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FTax
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "fTax")
data class FTaxEntity (
        @PrimaryKey(autoGenerate = true,)
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
        var fdivisionBean :Int = 0,

        var statusActive :Boolean = true,
        var created : Date = Date(),
        var modified : Date = Date(),
        var modifiedBy :String = ""//User ID
):Parcelable

internal fun FTaxEntity.toDomain(): FTax {
    return FTax(
            id = id,
            kode1 = kode1,
            description= description,

            taxPercent = taxPercent,

            accAccountTaxPurchaseBean = accAccountTaxPurchaseBean,
            accAccountTaxSalesBean = accAccountTaxSalesBean,


            fdivisionBean = FDivision(fdivisionBean),
            isStatusActive = statusActive,

            created = created!!,
            modified = modified!!,
            modifiedBy = modifiedBy!!
    )
}




