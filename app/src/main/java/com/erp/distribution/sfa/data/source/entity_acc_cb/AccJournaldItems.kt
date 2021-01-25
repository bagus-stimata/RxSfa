package com.erp.distribution.sfa.data.source.entity_acc_cb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "accjournald_items")
class AccJournaldItems : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id: Long = 0
    private val noUrut = 0
    private val description = ""
    private val amountDebit = 0.0
    private val amountCredit = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="accJournalhBean", referencedColumnName="refno")
    //    private AccJournalh accJournalhBean;
    private val accJournalhBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //    private AccAccount accAccountBean;
    private val accAccountBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID")
    //    private AccCostCenter accCostCenterBean;
    private val accCostCenterBean = 0

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 1L
    }
}