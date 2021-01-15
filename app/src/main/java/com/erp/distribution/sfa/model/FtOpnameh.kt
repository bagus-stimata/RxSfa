package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumTipeStockOpname
import java.util.*

//@Entity(tableName = "ftopnameh")
@Entity(tableName = "FtOpnameh")
class FtOpnameh {
    @PrimaryKey(autoGenerate = true)
    var refno: Long = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long = 0
    var noRek = ""
    var trDate = Date()
    var tipeStockOpname: EnumTipeStockOpname? = null

    /*
    * Account 
    */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //    private AccAccount accAccountBean;
    var accAccountBean = 0
    var notes = ""
    var isPosting = false
    var isEndOfDay = false
    var printCounter = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
    //	private FWarehouse fwarehouseBean;
    var fwarehouseBean = 0
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}