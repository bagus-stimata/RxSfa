package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fpromotion_rulesd_payments")
@Entity(tableName = "FPromotionRulesdPayments")
class FPromotionRulesdPayments {
    //Semua Giro dan Transfer masuk ke Giro
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBankBean", referencedColumnName="ID")
    //	private AccAccount accAccountBankBean; //Account ke Bank Mana Bayarnya
    /*
         * HARUS DIRUBAH JADI LONG
         */
    @PrimaryKey(autoGenerate = true)
    var id = 0

    //	@ManyToOne
    //	@JoinColumn(name="fpromotionRuleshBean", referencedColumnName="ID")
    //	private FPromotionRulesh fpromotionRuleshBean;
    var fpromotionRuleshBean = 0
    var noUrut = 0
    var trDate = Date()

    //Total Pembayaran:
    var amountPayRp = 0.0
    var potonganAmount = 0.0
    var transferAmountPay = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
    //	private FGiro ftransferBean;
    var ftransferBean = 0
    var giroAmountPay = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    var fgiroBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountPotonganBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountPotonganBean;
    var accAccountPotonganBean = 0
}