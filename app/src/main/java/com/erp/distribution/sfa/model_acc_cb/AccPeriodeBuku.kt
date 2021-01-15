package com.erp.distribution.sfa.model_acc_cb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "acc_periodebuku")
class AccPeriodeBuku : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    private val fdivisionBean = 0

    /*
	 * Komposite Key: Tidak boleh ada yang sama
	 */
    private val periodeYear = 0
    private val periodeMonth = 0

    /*
	 * Komposite Key: End
	 */
    private val periodeDateFrom: Date? = null
    private val periodeDateTo: Date? = null
    private val posting = false
    private val statusActive = true
    private val created = Date()
    private val modified = Date()
    private val modifiedBy = "" //User ID

    companion object {
        private const val serialVersionUID = 1L
    }
}