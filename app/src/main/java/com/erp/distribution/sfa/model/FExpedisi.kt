package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

//@Entity(tableName = "fexpedisi")
@Entity(tableName = "FExpedisi")
class FExpedisi : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = 0
    var kode1 = ""
    var kode2 = ""
    var name = ""
    var type = ""
    var address1 = ""
    var city1 = ""
    var state1 = ""
    var phone = ""
    var mobile = ""
    var email = ""
    var joindate = Date()
    var lasttrans = Date()
    var isStatusActive = false
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}