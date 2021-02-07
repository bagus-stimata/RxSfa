package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumCurrency
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class FVendor (
    var id :Int = 0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID :Int = 0,

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
    var isPkp:Boolean? = true,
    var namaPrshFakturPajak :String? = "",
    var namaPengusahaKenaPajak :String? = "",
    var npwp :String = "",
    var tanggalPengukuhanPkp:Date? = Date(),
    var isStatusActive: Boolean = true,
    var top :Int? = 0,

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    var wsport: String? = "",
    var isDisc1RegManual :Boolean? = false,
    var isDiscPlusRegManual :Boolean? = false,

    var created:Date? = Date(),
    var modified:Date? = Date(),
    var modifiedBy :String? = "" //User ID

): Parcelable