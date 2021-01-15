package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "ftsalesd_promo_tprb")
@Entity(tableName = "FtSalesdPromoTprb")
class FtSalesdPromoTprb : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var noUrut: Int? = null

    //	@ManyToOne
    //	@JoinColumn(name="ftSalesdFreegood", referencedColumnName="ID")
    //	private FtSalesdItems ftSalesdFreegood;	//Free Goodnya, bukan itemDetil yang mendapat
    var ftSalesdFreegood: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fpromoBean", referencedColumnName="ID")
    //	private FPromotionRulesh fpromoBean;
    var fpromoBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0

    //	@Column(name="PPRICE")
    //	private Double pprice =0;
    //	
    //	@Column(name="SPRICE")
    //	private Double sprice =0;
    var pricePcs_DetilItem = 0.0 //Sebelum PPN

    /*
    * PRICE in PCS: akan dipakai sebagai dasar perhitungan pada laporan aktifitas promosi
    * dan Dipakai untuk Perhitungan Piutang pada Principle pada Journal:
    */
    var pricePcsRpt = 0.0 //Sebelum PPN

    //	@Column(name="TPRB_QTY", length=8)
    //	private Integer tprbQty;
    var tprbQty = 0.0
}