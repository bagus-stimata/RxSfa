package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumAccTransactionType
import java.io.Serializable
import java.util.*

//@Entity(tableName = "fuang_muka")
@Entity(tableName = "FUangMuka")
class FUangMuka : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. xxxx
    */
    var sourceID = 0
    var noRek = ""
    var trDate = Date()

    /*
    * Ikut Cash Bank: Deposit or Payment
    */
    var accTransactionType: EnumAccTransactionType? = null
    var amountRp = 0.0
    var amountUsed = 0.0

    /*
    * Pencairan Giro
    * Tanggal Cair or Tolak Adalah Tanggal Transaksi Jurnal Akuntansi
    */
    //	@Enumerated(EnumType.ORDINAL)
    //	@Column(name="STATUS_GIRO")
    //	private EnumStatusGiro statusGiro;
    //	@Column(name="SHARED_TO_COMPANY")
    //	private boolean sharedToCompany=false; //setting Shared to company khusus ditaruh disini, bukan di divisi
    var cashAmountPay = 0.0
    var giroAmountPay = 0.0
    var transferAmountPay = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
    //	private FGiro ftransferBean;
    var ftransferBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
    //	private FGiro fgiroBean;
    var fgiroBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0
    var isSharedToCompany = false //setting Shared to company khusus ditaruh disini, bukan di divisi

    //Jika uang muka penjualan
    //	@ManyToOne
    //	@JoinColumn(name="fcustomerBean", referencedColumnName="ID")
    //	private FCustomer fcustomerBean;
    var fcustomerBean = 0

    //Jika uang muka pembelian
    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    var fvendorBean = 0

    /*
    * Account Mapping: Bank Account
    * Accaount untuk Pencairan Pada Piutang Giro Misalnya
    */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountDebitBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountDebitBean;
    var accAccountDebitBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountCreditBean", referencedColumnName="ID", nullable=true)
    //	AccAccount accAccountCreditBean;
    var accAccountCreditBean = 0
    var notes = ""
    var isStatusActive = true

    @Ignore
    var tempString = ""
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}