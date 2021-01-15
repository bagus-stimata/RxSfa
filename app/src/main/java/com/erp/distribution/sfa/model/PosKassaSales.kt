package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "poskassa_sales")
@Entity(tableName = "PosKassaSales")
class PosKassaSales {
    @PrimaryKey(autoGenerate = true)
    private val id: Long = 0

    /*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
    private val sourceID: Long = 0
    private val trDate = Date()

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    private val fdivisionBean = 0
    private val kassaStatusOpen = false
    private val description = ""
    private val timeOpen = Date()
    private val timeClose = Date()
    private val userOpen = ""
    private val userClose = ""
    private val totalSales = 0.0
    private val modalAwal100Ribu = 0
    private val modalAwal50Ribu = 0
    private val modalAwal20Ribu = 0
    private val modalAwal10Ribu = 0
    private val modalAwal5Ribu = 0
    private val modalAwal2Ribu = 0
    private val modalAwal1Ribu = 0
    private val modalAwal500 = 0
    private val modalAwal200 = 0
    private val modalAwal100 = 0
    private val modalAwalTotal = 0.0
    private val closing100Ribu = 0
    private val closing50Ribu = 0
    private val closing20Ribu = 0
    private val closing10Ribu = 0
    private val closing5Ribu = 0
    private val closing2Ribu = 0
    private val closing1Ribu = 0
    private val closing500 = 0
    private val closing200 = 0
    private val closing100 = 0
    private val closingTotal = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID")
    //	private FSalesman fsalesmanBean;
    private val fsalesmanBean = 0
}