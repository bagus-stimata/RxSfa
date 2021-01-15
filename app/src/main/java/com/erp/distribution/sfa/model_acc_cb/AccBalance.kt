package com.erp.distribution.sfa.model_acc_cb

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "acc_balance")
class AccBalance : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id: Long = 0

    //ACCOUNT: Dibuat syncron dengan peiode buku
    //	@ManyToOne
    //	@JoinColumn(name="accPeriodeBukuBean", referencedColumnName="ID")
    //	private AccPeriodeBuku accPeriodeBukuBean;
    private val accPeriodeBukuBean = 0

    /*
	 * END: ID of Balance
	 */
    //PERIODE
    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //	private AccAccount accAccountBean;
    private val accAccountBean = 0

    /*
	 * Cuma dipakai oleh 1 Account yaitu COGS
	 * dan ada Setiap Hari pada satu periode akuntansi
	 * Tidak visible: sulit untuk mengimplementasikan
	 */
    @Ignore
    private val amountMutasiDebit = 0.0

    @Ignore
    private val amountMutasiKredit = 0.0

    //SALDO AWAL: Dibuat Syncrond 
    private val amountBalanceAwal = 0.0

    //SALDO AKHIR
    private val amountBalance = 0.0

    companion object {
        private const val serialVersionUID = 1L
    }
}