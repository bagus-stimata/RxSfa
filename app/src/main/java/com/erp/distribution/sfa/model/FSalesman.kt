package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumReligion
import com.erp.distribution.sfa.model.modelenum.EnumSalesType
import java.util.*

//@Entity(tableName = "fsalesman")
@Entity(tableName = "FSalesman")
class FSalesman {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = 0
    var spcode = ""
    var spname = ""

    /*
    * TIPE JUAL SALESMAN
    * TO = Taking Order
    * C = Canvas
    * TF = Task Force
    * SHOP = Shop Sales
    */
    //	@Column(name="SALES_TYPE", length=5)
    //	private String salesType="";
    var salesType: EnumSalesType? = null

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0
    var address1 = ""
    var city1 = ""
    var state1 = ""
    var phone = ""
    var mobile = ""
    var whatsApp = ""
    var email = ""
    var joinDate = Date()
    var lastTrans = Date()
    var bornPlace = ""
    var bornDate = Date()
    var religion: EnumReligion? = null
    var isStatusActive = false
    var isWebServiceActive = false

    //FOR KASSA
    var isKassaStatusOpen = false
    var kassaIp = ""

    //	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean = 0

    /*
    * ignore/reject promotion rules setting
    */
    var isNoPromotionRules = false
    var isVendorcovered = false
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}