package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FCustomerGroup
import com.erp.distribution.sfa.domain.model.FDivision
import java.util.*

@Entity(tableName = "fCustomerGroup")
class FCustomerGroupEntity (
    @PrimaryKey
    var id : Int,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID : Int?,
    var kode1 : String,
    var kode2 : String,
    var description: String,
    var isStatusActive : Boolean,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean : Int?,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean : Int?,

    var created : Date? = Date(),
    var modified : Date? = Date(),
    var modifiedBy : String? = "" //User ID
)

internal fun FCustomerGroupEntity.toDomain(): FCustomerGroup {
    return FCustomerGroup(
        id = id,

        kode1 = kode1!!,
        description= description!!,
        isStatusActive = isStatusActive!!,

        fdivisionBean = fdivisionBean!!,
        ftPriceAlthBean = ftPriceAlthBean?.let { it }, //pakai !! tidak bisa dilakukan

        created = created!!,
        modified = modified!!,
        modifiedBy = modifiedBy!!

    )
}
