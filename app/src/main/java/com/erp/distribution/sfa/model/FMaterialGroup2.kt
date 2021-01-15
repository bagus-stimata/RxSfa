package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fmaterial_group2")
@Entity(tableName = "FMaterialGroup2")
class FMaterialGroup2 {
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

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup1Bean", referencedColumnName="ID")
    //	private FMaterialGroup1 fmaterialGroup1Bean;
    var fmaterialGroup1Bean = 0
    var isStatusActive = true
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}