package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "fparam_diskon_item_vendor")
@Entity(tableName = "FParamDiskonItemVendor")
class FParamDiskonItemVendor : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0
    var description = ""

    //QTY DIMANFAATKAN UNTUK RUPIAH
    var isQtyOrRupiah = false //true = Qty
    var nominal1 = 0.0 //Nominal or Qty
    var diskon1 = 0.0
    var diskon1plus = 0.0
    var nominal2 = 0.0
    var diskon2 = 0.0
    var diskon2plus = 0.0
    var nominal3 = 0.0
    var diskon3 = 0.0
    var diskon3plus = 0.0
    var nominal4 = 0.0
    var diskon4 = 0.0
    var diskon4plus = 0.0
    var nominal5 = 0.0
    var diskon5 = 0.0
    var diskon5plus = 0.0
    var isAllvendor = true

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    var fvendorBean = 0
    var isAllProductGroup = true

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3 fmaterialGroup3Bean;
    var fmaterialGroup3Bean = 0
    var isStatusActive = true

    companion object {
        const val serialVersionUID = 1L
    }
}