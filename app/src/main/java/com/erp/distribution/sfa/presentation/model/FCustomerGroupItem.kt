package com.erp.distribution.sfa.presentation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class FCustomerGroupItem (
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
    var isStatusActive : Boolean = false,

    //	private FDivision fdivisionBean;
    var fdivisionBean : Int = 0,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean : Int = 0,
    var created : Date = Date(),
    var modified : Date = Date(),
    var modifiedBy : String = "" //User ID
): Parcelable