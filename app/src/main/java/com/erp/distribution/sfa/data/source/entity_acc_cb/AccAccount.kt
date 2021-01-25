package com.erp.distribution.sfa.data.source.entity_acc_cb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumAccCoaType
import java.io.Serializable
import java.util.*

@Entity(tableName = "acc_account")
class AccAccount : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    private val sourceID = 0

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    private val fdivisionBean = 0
    private val kode1 = ""
    private val kode2 = ""
    private val coaType: EnumAccCoaType? = null
    private val titleGroup1 = ""
    private val titleGroup2 = ""
    private val description = ""

    //Boleh Dimasukin Saldo
    private val postEdit = true

    /*
	 * Biasanya dipakai untuk ayat silang yang digunakan untuk semua divisi dalam company
	 */
    private val usedForAllDiv = false
    private val statusActive = true

    /*
	 * Jika Level =1 
	 * maka adalah Top Level Account
	 */
    private val accLevel = 2

    /*
	 * Cicular Relation Ship
	 */
    //	@Column(name="parentAccount", length=30)
    //	@ManyToOne
    //	@JoinColumn(name = "parentAccount", nullable=true)
    //	private AccAccount accParent;
    private val parentAccount = 0
    private val created = Date()
    private val modified = Date()
    private val modifiedBy = "" //User ID

    companion object {
        private const val serialVersionUID = 1L
    }
}