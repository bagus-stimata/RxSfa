package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FMaterialGroup1
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "FMaterialGroup1")
data class FMaterialGroup1Entity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Int = 0,
    var kode1: String = "",
    var kode2: String = "",
    var description: String = "",

    //	private FDivision fdivisionBean;
    var fdivisionBean: Int = 0,
    var isStatusActive: Boolean = true,
    var created: Date = Date(),
    var modified: Date = Date(),
    var modifiedBy: String = "" //User ID

): Parcelable

internal fun FMaterialGroup1Entity.toDomain(): FMaterialGroup1{
    return FMaterialGroup1(
            id = id,
            sourceID = sourceID,
            kode1 = kode1,

            description = description,
            fdivisionBean = FDivision(fdivisionBean),
            isStatusActive = isStatusActive,

            created = created,
            modified = modified,
            modifiedBy = modifiedBy
    )
}