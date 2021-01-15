package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumAccTransactionType
import com.erp.distribution.sfa.model.modelenum.EnumStatusGiro
import java.io.Serializable
import java.util.*

/**
 * The persistent class for the BUKUGIRO database table.
 *
 */
//@Entity(tableName = "fgiro")
@Entity(tableName = "FGiro")
class FGiro : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    var sourceID: Long = 0
    var giroNumber = ""
    var bankName = ""
    var pemilikRek = ""

    /*
	 * Ikut Cash Bank: Deposit or Payment
	 */
    var accTransactionType: EnumAccTransactionType? = null
    var giroDate = Date()
    var giroDueDate = Date()
    var amountRp = 0.0
    var amountUsed = 0.0

    /*
	 * Pencairan Giro
	 * Tanggal Cair or Tolak Adalah Tanggal Transaksi Jurnal Akuntansi
	 */
    var statusGiro: EnumStatusGiro? = null
    var cairOrTolakDate = Date()
    var isSharedToCompany = false //setting Shared to company khusus ditaruh disini, bukan di divisi

    /*
	 * Account Mapping: Bank Account
	 * Accaount untuk Pencairan Pada Piutang Giro Misalnya
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID", nullable=true)
    //	private AccAccount accAccountBean;
    var accAccountBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountCairOrTolak", referencedColumnName="ID", nullable=true)
    //	AccAccount accAccountCairOrTolak;
    var accAccountCairOrTolak = 0

    /*
	 * true	= Giro
	 * false =  Transfer
	 */
    var isGiroOrTransfer = true

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0
    var isStatusActive = true

    @Ignore
    var tempString = ""
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}