package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTipeWarehouse
import java.io.Serializable
import java.util.*

//@Entity(tableName = "fwarehouse")
@Entity(tableName = "fWarehouse")
class FWarehouseEntity (
    @PrimaryKey(autoGenerate =false)
    var id: Int = -1,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID : Int =0,
    var kode1 : String ="",
    var kode2 : String ="",

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean : Int =0,
    var isProductHppSaved : Boolean? =false,
    var numberPriority: Int? = 0,
    var description : String ="",
    var isGudangUtama : Boolean? =false,
    var address1 : String ="",
    var city1: String? ="",
    var state1 : String ="",
    var phone : String ="",
    var isStatusActive : Boolean =false,
    var isGudangPo : Boolean? =false,
    var isGudangSo : Boolean? =false,
    var isGudangTransfer : Boolean? =false,
    var isGudangRetail : Boolean? =false,
    var isGudangPusatCompany : Boolean? =false,
    var isGudangTransitDiv : Boolean? =false,
    var tipeWarehouse: EnumTipeWarehouse? = EnumTipeWarehouse.GS,

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    var wsport : String? ="",
    var created : Date? = Date(),
    var modified : Date? = Date(),
    var modifiedBy : String? ="", //User ID
): Serializable