package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fstock")
@Entity(tableName = "fStock")
class FStockEntity (
    @PrimaryKey
    var refno: Long,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long,

    /*
    * HARI YANG DIPAKAI JUGA PADA HPP
    */
    var stockDate: Date = Date(),

    //	@Column(name="SALDO_AWAL")
    //	private Integer saldoAwal =0.0;
    var saldoAwal : Double,

    /*
    * NEXT AKAN DIBIKIN TRANSIEN
    */
    //	@Column(name="QTY_IN")
    //	private Integer qtyIn =0.0;
    //	@Column(name="QTY_OUT")
    //	private Integer qtyOut =0.0;
    var qtyIn : Double,
    var qtyOut : Double,

    //	@Column(name="QTY_ADJUST", length=10)	
    //	private Integer qtyAdjust =0.0;
    //	@Column(name="SALDO_AKHIR")
    //	private Integer saldoAkhir =0.0;
    var qtyAdjust : Double,
    var saldoAkhir : Double,

    /*
    * ORDERED STOCK
    */
    //	@Column(name="QTY_HOLD", length=9)	
    //	private Integer qtyHold =0.0;
    var qtyHold : Double,

    /*
    * HARGA BELI NET TERAKHIR
    * TAPI HARGA BELI TERAKHIR INI MENGGUNAKAN PPN (after PPN)
    */
    var closingPprice2_AfterPpn : Double,

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean;
    var fwarehouseBean : Int,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean : Int,
)