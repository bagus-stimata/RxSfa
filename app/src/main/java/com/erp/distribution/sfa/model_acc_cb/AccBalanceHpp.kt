package com.erp.distribution.sfa.model_acc_cb

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "acc_balancehpp")
class AccBalanceHpp : Serializable {
    @PrimaryKey(autoGenerate = true)
    private val id: Long = 0

    /*
	 * KESALAHAN DESIGN AWAL, KARENA PENGGUNAAN DIVISI
	 * DIVISI ADALAH DUMMY, JADI ABAIKAN
	 * DI ISI DUMMY
	 * 
	 * KEPUTUSAN UNTUK MEMAKAI DIVISI
	 * dengan Syarat
	 * Jika Divisi Adalah
	 * 
	 * diffCompanyAccount = true;
	 * 
	 */
    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    private val fdivisionBean = 0

    /*
	 * KALAU INI HITUGANNYA PER Akun(cuma Cogs) per Periode, setiap hari: ???
	 * Hitung balance dari COGS pakai jurnal, jadi tidak bisa pakai ini
	 */
    //	@ManyToOne
    //	@JoinColumn(name="accBalanceBean", referencedColumnName="ID")
    //	private AccBalance accBalanceBean;
    //##karena HPP maka mengambil dari PRODUCT
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    private val fmaterialBean = 0

    /*
	 * HARI YANG DIPAKAI JUGA PADA HPP
	 * HPP/COGS adalah harga barang Net SEBELUM PPN
	 */
    private val hppDate: Date? = null

    /*
	 * Qty Cuma Pembantu:
	 * Qty Diambil dari Qty pada Saldo Stok Real pada Perushaan Tersebut
	 */
    private val openingBalanceQty = 0
    private val openingBalanceTotAmount = 0.0

    //	@Column(name="OPENINGBALANCE_HPP")
    //	private Double openingBalanceHPP=0.0;
    @Ignore
    private val purchaseQty = 0

    @Ignore
    private val purchasePrice = 0.0

    @Ignore
    private val purchaseTotAmount = 0.0

    @Ignore
    private val soldQty = 0

    @Ignore
    private val soldPrice = 0.0

    @Ignore
    private val soldTotAmount = 0.0

    /*
	 * Qty Cuma Pembantu:
	 * Qty Diambil dari Qty pada Saldo Stok Real pada Perushaan Tersebut
	 */
    private val closingBalanceQty = 0
    private val closingBalanceTotAmount = 0.0

    //	@Column(name="CLOSINGBALANCE_HPP")
    //	private Double closingBalanceHPP=0.0;
    /*
	 * SALDO AKAN MENGGUNAKAN INI
	 */
    private val openingBalanceHppAverage = 0.0
    private val openingBalanceHppFifo = 0.0
    private val openingBalanceHppLifo = 0.0
    private val closingBalanceHppAverage = 0.0
    private val closingBalanceHppFifo = 0.0
    private val closingBalanceHppLifo = 0.0
}