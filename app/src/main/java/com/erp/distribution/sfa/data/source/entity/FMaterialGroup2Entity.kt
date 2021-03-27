package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FMaterialGroup1
import com.erp.distribution.sfa.domain.model.FMaterialGroup2
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "FMaterialGroup2")
data class FMaterialGroup2Entity (
        @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceId: Int = 0,
        var kode1: String = "",
        var kode2: String = "",
        var description: String = "",

    //	private FMaterialGroup1 fmaterialGroup1Bean;
        var fmaterialGroup1Bean: Int = 0,
        var statusActive: Boolean = true,
        var created: Date = Date(),
        var modified: Date = Date(),
        var modifiedBy: String = "" //User ID
): Parcelable

internal fun FMaterialGroup2Entity.toDomain(): FMaterialGroup2{
    return FMaterialGroup2(
            id = id,
            sourceId = sourceId,
            kode1 = kode1,

            description = description,
            fmaterialGroup1Bean = FMaterialGroup1(fmaterialGroup1Bean),
            isStatusActive = statusActive,

            created = created,
            modified = modified,
            modifiedBy = modifiedBy
    )
}