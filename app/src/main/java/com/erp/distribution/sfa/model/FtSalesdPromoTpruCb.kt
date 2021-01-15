package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "ftsalesd_promo_tprucb")
@Entity(tableName = "FtSalesdPromoTpruCb")
class FtSalesdPromoTpruCb : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val refnoPromo: Long = 0
    private val noUrut = 0

    //	@ManyToOne
    //	@JoinColumns({@JoinColumn(name="ftSalesdRefno", referencedColumnName="refno"),
    //		@JoinColumn(name="ftSalesdId", referencedColumnName="ID"),
    //		@JoinColumn(name="ftSalesdFreegood", referencedColumnName="freeGood")})
    //	@ManyToOne
    //	@JoinColumn(name="ftSalesdFreegood", referencedColumnName="ID")
    //	private FtSalesdItems ftSalesdBean;
    private val ftSalesdFreegood: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fPromoBean", referencedColumnName="ID")
    //	private FPromotionRulesh fPromoBean;
    private val fPromoBean = 0
    private val tpruCashback = 0.0
}