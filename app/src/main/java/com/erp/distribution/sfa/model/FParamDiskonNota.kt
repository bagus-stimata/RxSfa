package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit
import java.io.Serializable
import java.util.*

//@Entity(tableName = "fparam_diskon_nota")
@Entity(tableName = "FParamDiskonNota")
class FParamDiskonNota : Serializable {
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
    var description = ""

    //	@ManyToOne
    //	@JoinColumn(name="forFcustomerGroupBean", referencedColumnName="ID")
    //	private FCustomerGroup forFcustomerGroupBean;
    var forFcustomerGroupBean = 0
    var forTunaiKredit: EnumTunaiKredit? = null
    var isStatusActive = true

    /*
    * dimulai daribyNominal TERKECIL sampai terbesar: Programnya menaik donk
    */
    var buyNominal_1 = 0.0
    var disc1Get_1 = 0.0
    var disc2Get_1 = 0.0
    var discPlusGet_1 = 0.0
    var buyNominal_2 = 0.0
    var disc1Get_2 = 0.0
    var disc2Get_2 = 0.0
    var discPlusGet_2 = 0.0
    var buyNominal_3 = 0.0
    var disc1Get_3 = 0.0
    var disc2Get_3 = 0.0
    var discPlusGet_3 = 0.0
    var buyNominal_4 = 0.0
    var disc1Get_4 = 0.0
    var disc2Get_4 = 0.0
    var discPlusGet_4 = 0.0
    var buyNominal_5 = 0.0
    var disc1Get_5 = 0.0
    var disc2Get_5 = 0.0
    var discPlusGet_5 = 0.0
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID

    companion object {
        const val serialVersionUID = 1L
    }
}