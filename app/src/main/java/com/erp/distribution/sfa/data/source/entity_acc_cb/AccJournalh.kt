package com.erp.distribution.sfa.data.source.entity_acc_cb

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumAccTransactionType
import java.io.Serializable
import java.util.*

@Entity(tableName = "accjournalh")
class AccJournalh : Serializable {
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
    private val trDate = Date()
    private val notes = ""

    @Ignore
    private val tempNotes = ""
    private val amountDebit = 0.0
    private val amountCredit = 0.0
    private val posting = false
    private val postingTemp = false
    private val endOfDay = false

    //SUMBER J=JOURNAL, BP=Sales Order and Retur, AR=Account Receivable, PO=Purchase, AP=Account Payable
    //	@Column(name="SOURCE", length=5)
    //	private String source;
    private val accTransactionType: EnumAccTransactionType? = null

    /*
	 * Nomor refenrensi dari sumber. pasti berbeda kalau menggunakan refno ini
	 */
    private val sourceRefno: Long = 0
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