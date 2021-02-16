package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumReligion
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumSalesType
import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FSalesman
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.domain.model.FWarehouse
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
@Entity(tableName = "fSalesman")
data class FSalesmanEntity (
    @PrimaryKey
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
    var fdivisionBean : Int =0,
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

    /*
    * ignore/reject promotion rules setting
    */
    var isNoPromotionRules : Boolean = false,

    var created : Date? = Date(),
    var modified : Date? = Date(),
    var modifiedBy : String? ="" //User ID
) : Parcelable

internal fun FSalesmanEntity.toDomain(): FSalesman {
    return FSalesman(
            id = id,

            sourceID = sourceID,
            spcode = spcode,
            spname = spname,

            salesType = salesType,

            fdivisionBean = FDivision(fdivisionBean),
            address1 = address1,
            city1 = city1,
            state1 = state1,
            phone = phone,
            mobile = mobile,
            whatsApp = whatsApp,
            email = email,
            joinDate = joinDate,
            lastTrans = lastTrans,
            bornPlace = bornPlace,
            bornDate = bornDate,
            religion = religion,
            isStatusActive = isStatusActive,
            isWebServiceActive = isWebServiceActive,

            ftPriceAlthBean = let { ftPriceAlthBean },

            isNoPromotionRules = isNoPromotionRules,

            created = created,
            modified = modified,
            modifiedBy = modifiedBy
    )
}
