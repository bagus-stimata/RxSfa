package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialGroup3Entity
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
class FMaterialGroup3 (
        var id :Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID :Int =0,
        var kode1 :String ="",
        var description :String ="",
        var tempInt1 :Int =0,
        var tempInt2 :Int =0,
        var tempInt3 :Int =0,

    //	private FMaterialGroup2 fmaterialGroup2Bean;
        var fmaterialGroup2Bean :FMaterialGroup2 = FMaterialGroup2(),
        var isStatusActive :Boolean =false,
        var created : Date = Date(),
        var modified : Date = Date(),
        var modifiedBy :String ="" //User ID

): Parcelable {
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, theDecription: String): this(id = theId, description = theDecription)

}

internal fun FMaterialGroup3.toEntity(): FMaterialGroup3Entity {
    return FMaterialGroup3Entity(
            id = id,
            kode1 = kode1,
            description= description,

            fmaterialGroup2Bean = fmaterialGroup2Bean.id,
            isStatusActive = isStatusActive,

            created = created!!,
            modified = modified!!,
            modifiedBy = modifiedBy!!
    )
}
