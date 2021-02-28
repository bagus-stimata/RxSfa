package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.FtPriceAltdItems
import com.erp.distribution.sfa.domain.model.FtPriceAlth
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "FtPriceAltdItems")
data class FtPriceAltdItemsEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var sprice: Double =0.0,
    var spriceAfterPpn: Double =0.0,
    var sprice2: Double =0.0,
    var sprice2AfterPpn: Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID")
    //	private FtPriceAlth  ftPriceAlthBean;
    var ftPriceAlthBean: Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean: Int =0,
): Parcelable

internal fun FtPriceAltdItemsEntity.toDomain(): FtPriceAltdItems {
    return FtPriceAltdItems(
            id = id,
            sprice = sprice,
            spriceAfterPpn = sprice2AfterPpn,
            sprice2 = sprice2,
            sprice2AfterPpn = sprice2AfterPpn,

            ftPriceAlthBean = FtPriceAlth(ftPriceAlthBean),
            fmaterialBean = FMaterial(fmaterialBean)
    )
}
