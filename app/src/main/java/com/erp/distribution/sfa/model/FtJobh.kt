package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumTipeJob
import java.io.Serializable
import java.util.*

//@Entity(tableName = "ftjobh")
@Entity(tableName = "FtJobh")
class FtJobh : Serializable {
    @PrimaryKey(autoGenerate = true)
    var refno: Long = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long = 0

    /*
    * TRANSAKSI BISA DIMULAI DARI PURCHASE INVOICE ATAU PO
    */
    var noDoc = ""
    var docDate = Date()
    var tipeJob = EnumTipeJob.JOB_1

    /*
* MENGGUNAKAN ISTILAH PARAMETER AGAR LEBIH FLEXIBLE	
*/
    var paramInt1 = 0
    var paramInt2 = 0
    var paramInt3 = 0
    var paramInt4 = 0
    var paramInt5 = 0
    var paramDate1 = Date()
    var isEndOfDay = false
    var isProses = false
    var notes = ""

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