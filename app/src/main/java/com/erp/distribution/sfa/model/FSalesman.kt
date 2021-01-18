package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumReligion
import com.erp.distribution.sfa.model.modelenum.EnumSalesType
import java.util.*

//@Entity(tableName = "fsalesman")
@Entity(tableName = "fSalesman")
class FSalesman (
    @PrimaryKey
    var id : Int,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID : Int,
    var spcode : String,
    var spname : String,

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
    var fdivisionBean : Int,
    var address1 : String,
    var city1 : String,
    var state1 : String,
    var phone : String,
    var mobile : String,
    var whatsApp : String,
    var email : String,
    var joinDate : Date = Date(),
    var lastTrans : Date = Date(),
    var bornPlace : String,
    var bornDate : Date = Date(),
    var religion: EnumReligion = EnumReligion.OTH1,
    var isStatusActive : Boolean,
    var isWebServiceActive : Boolean,

    //FOR KASSA
    var isKassaStatusOpen : Boolean,
    var kassaIp : String,

    //	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean : Int,

    /*
    * ignore/reject promotion rules setting
    */
    var isNoPromotionRules : Boolean,
    var isVendorcovered : Boolean,
    var created : Date = Date(),
    var modified : Date = Date(),
    var modifiedBy : String //User ID
)