package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumCurrency
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class FVendor (
    val id :Int = 0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    val sourceId :Int = 0,

//    val fdivisionBean : FDivisionEntity? = FDivisionEntity(),
    val fdivisionBean : FDivision = FDivision(),
    val vcode :String = "",
    val vname :String = "",
    val address1 :String = "",
    val address2 :String = "",
    val city1 :String = "",
    val state1 :String = "",
    val phone :String = "",
    val email :String = "",
    val joinDate:Date = Date(),
    val lastTrans:Date = Date(),
    val noRekening :String = "",
    val currency: EnumCurrency? = EnumCurrency.IDR,

    /*
    * Diskon Margin Barang: disc2 & Disc2Plus
    */
    val disc2Margin :Double = 0.0,
    val disc1PlusMargin :Double = 0.0,

    /*
    * PERPAJAKAN
    */
    val isPkp:Boolean? = true,
    val namaPrshFakturPajak :String? = "",
    val namaPengusahaKenaPajak :String? = "",
    val npwp :String = "",
    val tanggalPengukuhanPkp:Date? = Date(),
    val isStatusActive: Boolean = true,
    val top :Int? = 0,

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    val wsport: String? = "",
    val isDisc1RegManual :Boolean? = false,
    val isDiscPlusRegManual :Boolean? = false,

    val created:Date? = Date(),
    val modified:Date? = Date(),
    val modifiedBy :String? = "" //User ID

): Parcelable {
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, theVname: String): this(id = theId, vname = theVname)

}