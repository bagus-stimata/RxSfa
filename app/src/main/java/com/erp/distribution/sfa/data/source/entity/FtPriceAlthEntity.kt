package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FtPriceAlth
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "FtPriceAlth")
data class FtPriceAlthEntity (
        @PrimaryKey(autoGenerate = true)
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
        var fdivisionBean : Int =0,
        var trDate: Date = Date(),
        var statusActive: Boolean = true, //yang ditarik pasti yang aktif

        var created: Date = Date(),
        var modified: Date = Date(),
        var modifiedBy : String =""
): Parcelable


internal fun FtPriceAlthEntity.toDomain(): FtPriceAlth {
    return FtPriceAlth(
            id = id,
            noRek = noRek,
            description= description,

            fdivisionBean = FDivision(fdivisionBean),
            trDate = trDate,
            isStatusActive = statusActive,

            created = created!!,
            modified = modified!!,
            modifiedBy = modifiedBy!!
    )
}