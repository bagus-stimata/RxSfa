package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumTipeWarehouse
import java.util.*

//@Entity(tableName = "fwarehouse")
@Entity(tableName = "FWarehouse")
class FWarehouse {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = 0
    var kode1 = ""
    var kode2 = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0
    var isProductHppSaved = false
    var numberPriority: Int? = null
    var description = ""
    var isGudangUtama = false
    var address1 = ""
    var city1: String? = null
    var state1 = ""
    var phone = ""
    var isStatusActive = false
    var isGudangPo = true
    var isGudangSo = true
    var isGudangTransfer = true
    var isGudangRetail = true
    var isGudangPusatCompany = true
    var isGudangTransitDiv = true
    var tipeWarehouse: EnumTipeWarehouse? = null

    //PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
    var wsport = ""
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}