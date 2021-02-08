package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fsub_area")
@Entity(tableName = "fSubArea")
data class FSubAreaEntity (
    @PrimaryKey
    var id : Int = 0, 

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID : Int = 0, 
    var kode1 : String = "", 
    var kode2 : String = "", 
    var description: String = "",

    //	@ManyToOne
    //	@JoinColumn(name="fareaBean", referencedColumnName="ID")
    //	private FArea fareaBean;
    var fareaBean : Int = 0, 

    //	@OneToMany(mappedBy="fsubareaBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    var isStatusActive: Boolean = false,
    var created : Date = Date(), 
    var modified : Date = Date(), 
    var modifiedBy : String = ""  //User ID
)