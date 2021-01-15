package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "ftarpaymentd")
@Entity(tableName = "FtArPaymentd")
class FtArPaymentd : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var noUrut = 0
    var cashAmountPay = 0.0
    var uangMukaAmountPay = 0.0
    var returAmountPay = 0.0
    var giroAmountPay = 0.0
    var transferAmountPay = 0.0
    var kelebihanBayarAmount = 0.0

    //	@Column(name="SUBTOTAL_PAY")
    //	private Double subtotalPay=0.0;
    var potonganAmount = 0.0

    /*
    * DIPAKAI NOTE SECARA UMUM DALAM SATU TRANSAKSI
    */
    var potonganNotes = ""

    //	@ManyToOne
    //	@JoinColumn(name="ftArPaymenthBean", referencedColumnName="refno")
    //	private FtArPaymenth ftArPaymenthBean;
    var ftArPaymenthBean = 0

    /*
    * Dibayarkan untuk Faktur atau Potong retur
    */
    //	@ManyToOne
    //	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
    //	private FtSalesh ftSaleshBean;
    var ftSaleshBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="returBean", referencedColumnName="refno")
    //	private FtSalesh returBean;
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
    //	private AccAccount accAccountPotonganBean;
    var accAccountPotonganBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fuangMukaBean", referencedColumnName="ID")
    //	private FUangMuka fuangMukaBean; // Hutang(D) pada pada Piutang(K) -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
    var fuangMukaBean = 0
}