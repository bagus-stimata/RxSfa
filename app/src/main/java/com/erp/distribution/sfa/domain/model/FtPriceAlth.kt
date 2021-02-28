package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.FAreaEntity
import com.erp.distribution.sfa.data.source.entity.FtPriceAlthEntity
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FtPriceAlth (
        var id : Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var noRek : String ="",
        var description : String ="",

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
        var fdivisionBean : FDivision = FDivision(),
        var trDate: Date = Date(),
        var isStatusActive: Boolean = true, //yang ditarik pasti yang aktif

        var created: Date = Date(),
        var modified: Date = Date(),
        var modifiedBy : String =""
): Parcelable

internal fun FtPriceAlth.toEntity(): FtPriceAlthEntity {
    return FtPriceAlthEntity(
            id = id,
            noRek = noRek,
            description= description,

            fdivisionBean = fdivisionBean.id,
            trDate = trDate,
            isStatusActive = isStatusActive,

            created = created!!,
            modified = modified!!,
            modifiedBy = modifiedBy!!
    )
}



