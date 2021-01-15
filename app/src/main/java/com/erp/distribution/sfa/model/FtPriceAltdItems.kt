package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "ftpricealtditems")
@Entity(tableName = "FtPriceAltdItems")
class FtPriceAltdItems {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var noUrut = 0
    var pprice = 0.0
    var ppriceAfterPpn: Double? = null
    var pprice2 = 0.0
    var pprice2AfterPpn = 0.0
    var sprice = 0.0
    var spriceAfterPpn: Double? = null
    var sprice2 = 0.0
    var sprice2AfterPpn = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID")
    //	private FtPriceAlth  ftPriceAlthBean;
    var ftPriceAlthBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0
}