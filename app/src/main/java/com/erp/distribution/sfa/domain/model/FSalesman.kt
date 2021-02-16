package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumReligion
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumSalesType
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
data class FSalesman (
    var id : Int = -1,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID : Int =0,
    var spcode : String ="",
    var spname : String ="",

    /*
    * TIPE JUAL SALESMAN
    * TO = Taking Order
    * C = Canvas
    * TF = Task Force
    * SHOP = Shop Sales
    */
    //	@Column(name="SALES_TYPE", length=5)
    //	private String salesType="";
    var salesType: EnumSalesType = EnumSalesType.TO,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean : FDivision =FDivision(),
    var address1 : String ="",
    var city1 : String ="",
    var state1 : String ="",
    var phone : String ="",
    var mobile : String ="",
    var whatsApp : String ="",
    var email : String ="",
    var joinDate : Date = Date(),
    var lastTrans : Date = Date(),
    var bornPlace : String ="",
    var bornDate : Date = Date(),
    var religion: EnumReligion? = EnumReligion.OTH1,
    var isStatusActive : Boolean =false,
    var isWebServiceActive : Boolean =false,

    //	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean : Int? =0,

    var ftSaleshSet : List<FtSalesh> = listOf<FtSalesh>(),

        /*
        * ignore/reject promotion rules setting
        */
    var isNoPromotionRules : Boolean = false,
    var isVendorcovered : Boolean = false,
    var created : Date? = Date(),
    var modified : Date? = Date(),
    var modifiedBy : String? ="" //User ID

): Parcelable {
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, theSpname: String): this(id = theId, spname = theSpname)

}