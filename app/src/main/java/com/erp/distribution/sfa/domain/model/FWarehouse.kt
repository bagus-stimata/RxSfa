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
    val id: Int = -1,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    val sourceID : Int =0,
    val kode1 : String ="",
    val kode2 : String ="",

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    val fdivisionBean : Int =0,
    val description : String ="",
    val isGudangUtama : Boolean? =false,
    val address1 : String ="",
    val city1: String? ="",
    val state1 : String ="",
    val phone : String ="",
    val isStatusActive : Boolean =false,
    val tipeWarehouse: EnumTipeWarehouse? = EnumTipeWarehouse.GS,

    val ftSaleshSet : List<FtSalesh> = listOf<FtSalesh>(),

        //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    val wsport : String? ="",
    val created : Date? = Date(),
    val modified : Date? = Date(),
    val modifiedBy : String? ="", //User ID
): Model(), Serializable, Parcelable {
    constructor(theId: Int): this(id = theId)

}