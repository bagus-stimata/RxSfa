package com.erp.distribution.sfa.model_acc_cb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumAccTransactionType
import java.io.Serializable
import java.util.*

@Entity(tableName = "cb_mutasikash")
class CbMutasiKash : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val refno: Long = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    private val sourceID: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
    //	private FDivision fdivisionBean;
    private val fdivisionBean = 0
    private val noRek = ""
    private val cekNumber = ""
    private val trDate = Date()
    private val payee = ""
    private val notes = ""

    /*
	 * JIKA: true maka jurnalnya berbeda
	 * 
	 */
    private val mutasiKas = false
    private val mutasiAntarPT = false
    private val mutasiKonfirm = false

    /*
	 * Dipakai untuk Settlemen dengan AR vs Kasir
	 */
    //	@ManyToOne
    //	@JoinColumn(name="payeeBean", referencedColumnName="ID", nullable=true)
    //	private FSalesman payeeBean;
    private val payeeBean = 0
    private val amount = 0.0
    private val endOfDay = false

    //SUMBER J=JOURNAL, BP=Sales Order and Retur, AR=Account Receivable, PO=Purchase, AP=Account Payable
    //	@Column(name="SOURCE", length=5)
    //	private String source= 0;
    private val accTransactionType: EnumAccTransactionType? = null

    /*
	 * Account Bank Only: Jadi harus difilter
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID", nullable=false)
    //    private AccAccount accAccountBean;
    private val accAccountBean = 0
    private val created = Date()
    private val modified = Date()
    private val modifiedBy = "" //User ID

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 1L
    }
}