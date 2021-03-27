package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FMaterialGroup2
import com.erp.distribution.sfa.domain.model.FMaterialGroup3
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "FMaterialGroup3")
class FMaterialGroup3Entity (
        @PrimaryKey(autoGenerate=false)
    var id :Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceId :Int =0,
        var kode1 :String ="",
        var kode2 :String ="",
        var description :String ="",
        var tempInt1 :Int =0,
        var tempInt2 :Int =0,
        var tempInt3 :Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup2Bean", referencedColumnName="ID")
    //	private FMaterialGroup2 fmaterialGroup2Bean;
        var fmaterialGroup2Bean :Int =0,
        var statusActive :Boolean =false,
        var created : Date = Date(),
        var modified : Date = Date(),
        var modifiedBy :String ="" //User ID

): Parcelable

internal fun FMaterialGroup3Entity.toDomain(): FMaterialGroup3 {
    return FMaterialGroup3(
            id = id,
            kode1 = kode1,
            description= description,

            fmaterialGroup2Bean = FMaterialGroup2(fmaterialGroup2Bean),
            isStatusActive = statusActive,

            created = created!!,
            modified = modified!!,
            modifiedBy = modifiedBy!!
    )
}

