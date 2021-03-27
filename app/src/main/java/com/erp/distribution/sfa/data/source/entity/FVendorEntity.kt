package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumCurrency
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FVendor
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "fVendor")
class FVendorEntity (
        @PrimaryKey(autoGenerate = true)
    var id :Int = 0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceId :Int = 0,

//    var fdivisionBean : FDivisionEntity? = FDivisionEntity(),
        var fdivisionBean : Int = 0,
        var vcode :String = "",
        var vname :String = "",
        var address1 :String = "",
        var address2 :String = "",
        var city1 :String = "",
        var state1 :String = "",
        var phone :String = "",
        var email :String = "",
        var joinDate:Date = Date(),
        var lastTrans:Date = Date(),
        var noRekening :String = "",
        var currency: EnumCurrency? = EnumCurrency.IDR,

    /*
    * Diskon Margin Barang: disc2 & Disc2Plus
    */
    var disc2Margin :Double = 0.0,
        var disc1PlusMargin :Double = 0.0,

    /*
    * PERPAJAKAN
    */
    var pkp:Boolean? = true,
        var namaPrshFakturPajak :String? = "",
        var namaPengusahaKenaPajak :String? = "",
        var npwp :String = "",
        var tanggalPengukuhanPkp:Date? = Date(),
        var statusActive: Boolean = true,
        var top :Int? = 0,

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
        var wsport: String? = "",
        var disc1RegManual :Boolean? = false,
        var discPlusRegManual :Boolean? = false,

        var created:Date? = Date(),
        var modified:Date? = Date(),
        var modifiedBy :String? = "" //User ID

): Parcelable

internal fun FVendorEntity.toDomain(): FVendor {
    return FVendor(
            id = id,
            sourceId = sourceId,
            vcode = vcode,
            vname = vname,

            fdivisionBean = FDivision(fdivisionBean),
            isStatusActive = statusActive,

            address1 = address1,
            address2 = address2,
            city1 = city1,
            state1 = state1,
            phone = phone,
            email = email,
            joinDate = joinDate,
            lastTrans = lastTrans,
            noRekening = noRekening,
            currency = currency,
            disc2Margin = disc2Margin,

            disc1PlusMargin = disc1PlusMargin,
            pkp = pkp,

            top = top,
            disc1RegManual = disc1RegManual,
            discPlusRegManual = discPlusRegManual,

            created = created!!,
            modified = modified!!,
            modifiedBy = modifiedBy!!
    )
}

