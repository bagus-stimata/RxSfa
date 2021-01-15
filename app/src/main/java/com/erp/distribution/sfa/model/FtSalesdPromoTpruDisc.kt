package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "ftsalesd_promo_tprudisc")
@Entity(tableName = "FtSalesdPromoTpruDisc")
class FtSalesdPromoTpruDisc : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var noUrut = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftSalesdBean", referencedColumnName="ID")
    //	private FtSalesdItems ftSalesdBean;
    var ftSalesdBean: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fPromoBean", referencedColumnName="ID")
    //	private FPromotionRulesh fPromoBean;
    private var fPromoBean = 0

    /*
	 * PRICE in PCS: akan dipakai sebagai dasar perhitungan pada laporan aktifitas promosi
	 * dan Dipakai untuk Perhitungan Piutang pada Principle pada Journal:
	 */
    var pricePcsRpt = 0.0
    var disc1: Double? = null

    /*
    * KHUSUS PEMBERIAN BERUPA VALUE: 
    */
    var disc1Value_Uom1 = 0.0

    //==============
    var disc2 = 0.0
    var disc3 = 0.0
    var disc1Plus = 0.0
    var disc2Plus = 0.0
    fun getfPromoBean(): Int {
        return fPromoBean
    }

    fun setfPromoBean(fPromoBean: Int) {
        this.fPromoBean = fPromoBean
    }
}