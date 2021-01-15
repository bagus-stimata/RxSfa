package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fstock")
@Entity(tableName = "FStock")
class FStock {
    @PrimaryKey(autoGenerate = true)
    var refno: Long = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long = 0

    /*
    * HARI YANG DIPAKAI JUGA PADA HPP
    */
    var stockDate: Date? = null

    //	@Column(name="SALDO_AWAL")
    //	private Integer saldoAwal =0.0;
    var saldoAwal = 0.0

    /*
    * NEXT AKAN DIBIKIN TRANSIEN
    */
    //	@Column(name="QTY_IN")
    //	private Integer qtyIn =0.0;
    //	@Column(name="QTY_OUT")
    //	private Integer qtyOut =0.0;
    var qtyIn = 0.0
    var qtyOut = 0.0

    //	@Column(name="QTY_ADJUST", length=10)	
    //	private Integer qtyAdjust =0.0;
    //	@Column(name="SALDO_AKHIR")
    //	private Integer saldoAkhir =0.0;
    var qtyAdjust = 0.0
    var saldoAkhir = 0.0

    /*
    * ORDERED STOCK
    */
    //	@Column(name="QTY_HOLD", length=9)	
    //	private Integer qtyHold =0.0;
    var qtyHold = 0.0

    /*
    * HARGA BELI NET TERAKHIR
    * TAPI HARGA BELI TERAKHIR INI MENGGUNAKAN PPN (after PPN)
    */
    var closingPprice2_AfterPpn = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean;
    var fwarehouseBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0
}