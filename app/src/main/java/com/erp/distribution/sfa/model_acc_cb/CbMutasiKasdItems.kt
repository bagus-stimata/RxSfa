package com.erp.distribution.sfa.model_acc_cb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cb_mutasikasd_items")
class CbMutasiKasdItems : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id: Long = 0
    private val noUrut = 0
    private val description = ""

    //	@ManyToOne
    //	@JoinColumn(name="cbMutasiKashBean", referencedColumnName="refno", nullable=false)
    //    private CbMutasiKash cbMutasiKashBean;
    private val cbMutasiKashBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
    //    private AccAccount accAccountBean;
    private val accAccountBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID")
    //    private AccCostCenter accCostCenterBean;
    private val accCostCenterBean = 0
    private val amount = 0.0

    companion object {
        /**
         *
         */
        private const val serialVersionUID = 1L
    }
}