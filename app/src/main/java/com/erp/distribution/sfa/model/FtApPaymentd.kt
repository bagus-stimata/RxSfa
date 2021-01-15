package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "ftAppaymentd")
@Entity(tableName = "FtApPaymentd")
class FtApPaymentd {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var noUrut = 0
    var cashAmountPay = 0.0
    var uangMukaAmountPay = 0.0
    var returAmountPay = 0.0
    var promotionAmountPay =
        0.0 //Jika ada potongan lain semacam DCV maka langsung saja dimasukkan Potongan
    var giroAmountPay = 0.0
    var transferAmountPay = 0.0
    var kelebihanBayarAmount = 0.0

    //	@Column(name="SUBTOTAL_PAY")
    //	private Double subtotalPay=0.0;
    var potonganAmount = 0.0
    var potonganNotes = ""

    //	@ManyToOne
    //	@JoinColumn(name="ftApPaymenthBean", referencedColumnName="refno")
    //	private FtApPaymenth ftApPaymenthBean;
    var ftApPaymenthBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
    //	private FtPurchaseh ftPurchasehBean;
    var ftPurchasehBean = 0

    /*
    * Dibayarkan untuk Faktur atau Potong retur
    */
    //	@ManyToOne
    //	@JoinColumn(name="returBean", referencedColumnName="refno")
    //	private FtPurchaseh returBean;
    var returBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
    //	private FGiro ftransferBean;
    var ftransferBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    var fgiroBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountPotonganBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountPotonganBean; // Hutang(D) pada Biaya Potongan -> Hutang berkurang di Debet dan Biaya Potongan bertambah di Kredit
    var accAccountPotonganBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fpromotionRuleshBean", referencedColumnName="ID")
    //	private FPromotionRulesh fpromotionRuleshBean; // Hutang(D) pada pada Piutang(K) -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
    var fpromotionRuleshBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fuangMukaBean", referencedColumnName="ID")
    //	private FUangMuka fuangMukaBean; // Hutang(D) pada pada Uang Muka -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
    var fuangMukaBean = 0
}