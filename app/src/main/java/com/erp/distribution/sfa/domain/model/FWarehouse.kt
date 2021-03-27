package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTipeWarehouse
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
class FWarehouse (
        var id: Int = -1,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceId : Int =0,
        var kode1 : String ="",
        var kode2 : String ="",

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
        var fdivisionBean : FDivision =FDivision(),
        var description : String ="",
        var isGudangUtama : Boolean? =false,
        var address1 : String ="",
        var city1: String? ="",
        var state1 : String ="",
        var phone : String ="",
        var statusActive : Boolean =false,
        var tipeWarehouse: EnumTipeWarehouse? = EnumTipeWarehouse.GS,

        var ftSaleshSet : List<FtSalesh> = listOf<FtSalesh>(),

        //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
        var wsport : String? ="",
        var created : Date? = Date(),
        var modified : Date? = Date(),
        var modifiedBy : String? ="", //User ID

): Parcelable {
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, theDecription: String): this(id = theId, description = theDecription)

}