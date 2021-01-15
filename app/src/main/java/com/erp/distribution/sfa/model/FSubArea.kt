package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fsub_area")
@Entity(tableName = "FSubArea")
class FSubArea {
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
    var description: String? = null

    //	@ManyToOne
    //	@JoinColumn(name="fareaBean", referencedColumnName="ID")
    //	private FArea fareaBean;
    var fareaBean = 0

    //	@OneToMany(mappedBy="fsubareaBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    var isStatusActive = true
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}