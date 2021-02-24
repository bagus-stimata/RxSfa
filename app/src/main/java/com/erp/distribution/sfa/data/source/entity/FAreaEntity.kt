package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.erp.distribution.sfa.domain.model.FArea
import com.erp.distribution.sfa.domain.model.FDivision
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "fArea")
@Parcelize
data class FAreaEntity (
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

    //	private FDivision fdivisionBean;
    var fdivisionBean: Int = 0,

    //	private FRegion fregionBean;
    var fregionBean: Int = 0,

    var isStatusActive: Boolean = true,

    var created: Date = Date(),
    var modified: Date = Date(),
    var modifiedBy: String = "" //User ID
): Parcelable


internal fun FAreaEntity.toDomain(): FArea {
    return FArea(
            id = id,
            kode1 = kode1,
            description= description,
            sourceId = sourceId,

            fdivisionBean = FDivision(fdivisionBean),
            isStatusActive = isStatusActive,

            created = created!!,
            modified = modified!!,
            modifiedBy = modifiedBy!!
    )
}

data class FAreaWithFDivision(
        @Embedded val fAreaEntity: FAreaEntity,
        @Relation(
                parentColumn = "fdivisionBean",
                entityColumn = "id"
        )
        val fDivisionEntity: FDivisionEntity
)