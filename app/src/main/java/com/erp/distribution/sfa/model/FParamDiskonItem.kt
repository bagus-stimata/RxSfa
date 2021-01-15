package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

//@Entity(tableName = "fparam_diskon_item")
@Entity(tableName = "FParamDiskonItem")
class FParamDiskonItem : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long = 0
    var noRek = ""
    var description = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    //Value
    var nominal1 = 0.0
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

    //Qty In PCS
    var qtyLebihDari1 = 0
    var diskonFromQty1 = 0.0
    var diskonFromQty1plus = 0.0
    var qtyLebihDari2 = 0
    var diskonFromQty2 = 0.0
    var diskonFromQty2plus = 0.0
    var qtyLebihDari3 = 0
    var diskonFromQty3 = 0.0
    var diskonFromQty3plus = 0.0
    var qtyLebihDari4 = 0
    var diskonFromQty4 = 0.0
    var diskonFromQty4plus = 0.0
    var qtyLebihDari5 = 0
    var diskonFromQty5 = 0.0
    var diskonFromQty5plus = 0.0
    var isAllvendor = false

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    var fvendorBean = 0
    var isAllsubgrup = false

    //	@ManyToOne
    //	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
    //	private FCustomerGroup fcustomerGroupBean;
    var fcustomerGroupBean = 0
    var isAllproductgrup = false

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3	fmaterialGroup3Bean;
    var fmaterialGroup3Bean = 0
    var isAlltunaikredit = false
    var tunaikredit = ""
    var isStatusActive = false
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID

    companion object {
        const val serialVersionUID = 1L
    }
}