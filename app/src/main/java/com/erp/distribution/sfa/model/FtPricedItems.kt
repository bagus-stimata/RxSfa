package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "ftpriced_items")
@Entity(tableName = "FtPricedItems")
class FtPricedItems {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var noUrut = 0
    var pprice = 0.0
    var ppriceAfterPpn = 0.0
    var oldPprice = 0.0
    var sprice = 0.0
    var spriceAfterPpn = 0.0
    var oldSprice = 0.0
    var oldSpriceAfterPpn = 0.0
    var pprice2 = 0.0
    var pprice2AfterPpn = 0.0
    var oldPprice2 = 0.0
    var sprice2 = 0.0
    var sprice2AfterPpn: Double? = null
    var oldSprice2 = 0.0
    var oldSprice2AfterPpn = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftPricehBean", referencedColumnName="refno", insertable=true, updatable=true)
    //	private FtPriceh  ftPricehBean;
    var ftPricehBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID", insertable=true, updatable=true)
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0
}