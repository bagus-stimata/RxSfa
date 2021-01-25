package com.erp.distribution.sfa.data.source.entity_acc_cb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

/**
 * The persistent class for the ACCOUNTGROUP database table.
 *
 */
@Entity(tableName = "acc_costcenter")
class AccCostCenter : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    private val sourceID = 0
    private val kode1 = ""
    private val kode2 = ""
    private val description = ""

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    private val fdivisionBean = 0
    private val statusActive = true
    private val created = Date()
    private val modified = Date()
    private val modifiedBy = "" //User ID

    companion object {
        private const val serialVersionUID = 1L
    }
}