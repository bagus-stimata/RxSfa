package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

//@Entity(tableName = "ftax")
@Entity(tableName = "FTax")
class FTax : Serializable {
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
    var description = ""
    var taxPercent = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountTaxPurchaseBean", referencedColumnName="ID")
    //	private AccAccount accAccountTaxPurchaseBean;
    var accAccountTaxPurchaseBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountTaxSalesBean", referencedColumnName="ID")
    //	private AccAccount accAccountTaxSalesBean;
    var accAccountTaxSalesBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0
    var isStatusActive = true
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}