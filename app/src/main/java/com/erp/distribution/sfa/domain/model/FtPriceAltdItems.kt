package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.FtPriceAltdItemsEntity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "FtPriceAltdItems")
class FtPriceAltdItems (
        @PrimaryKey(autoGenerate = true)
    var id: Int =0,
        var sprice: Double =0.0,
        var spriceAfterPpn: Double =0.0,
        var sprice2: Double =0.0,
        var sprice2AfterPpn: Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID")
    //	private FtPriceAlth  ftPriceAlthBean;
        var ftPriceAlthBean: FtPriceAlth = FtPriceAlth(),

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
        var fmaterialBean: FMaterial = FMaterial(),
): Parcelable


internal fun FtPriceAltdItems.toEntity(): FtPriceAltdItemsEntity {
    return FtPriceAltdItemsEntity(
            id = id,
            sprice = sprice,
            spriceAfterPpn = sprice2AfterPpn,
            sprice2 = sprice2,
            sprice2AfterPpn = sprice2AfterPpn,

            ftPriceAlthBean = ftPriceAlthBean.id,
            fmaterialBean = fmaterialBean.id
    )
}
