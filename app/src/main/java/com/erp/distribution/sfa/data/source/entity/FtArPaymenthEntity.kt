package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

//@Entity(tableName = "ftarpaymenth")
@Entity(tableName = "FtArPaymenth")
class FtArPaymenthEntity : Serializable {
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
    var entryDate = Date()
    var notes = ""
    var printCounter = 0

    //PENGGUNAAN UTAMA PADA END_OF_DAY
    var endOfDay = false

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    /*
    * Dipakai untuk Settlemen dengan AR vs Kasir
    */
    //	@ManyToOne
    //	@JoinColumn(name="payeeBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman payeeBean;
    var payeeBean = 0
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}