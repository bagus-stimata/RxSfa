package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "FtPricedItems")
data class FtPricedItemsEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    var pprice : Double=0.0,
    var ppriceAfterPpn : Double=0.0,
    var sprice : Double=0.0,
    var spriceAfterPpn : Double=0.0,
    var pprice2 : Double=0.0,
    var pprice2AfterPpn : Double=0.0,
    var sprice2 : Double=0.0,
    var sprice2AfterPpn: Double = 0.0,

    //	@ManyToOne
    //	@JoinColumn(name="ftPricehBean", referencedColumnName="refno", insertable=true, updatable=true)
    //	private FtPriceh  ftPricehBean;
    var ftPricehBean : Int=0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID", insertable=true, updatable=true)
    //	private FMaterial fmaterialBean;
    var fmaterialBean : Int=0,

): Parcelable