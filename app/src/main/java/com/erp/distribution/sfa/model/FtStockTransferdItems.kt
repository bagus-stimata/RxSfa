package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//@Entity(tableName = "ftstocktransferd_items")
@Entity(tableName = "FtStockTransferdItems")
class FtStockTransferdItems {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var noUrut = 0

    /*
    * Dasar harga total
    */
    var pprice = 0.0 //Harga disimpan dalam Satuan Besar Sebelum Ppn

    @Ignore
    var ppriceUom2 = 0.0

    @Ignore
    var ppriceUom3 = 0.0

    @Ignore
    var ppriceUom4 = 0.0

    //	@Ignore
    //	private Integer qty1=0.0;
    //	@Ignore
    //	private Integer qty2=0.0;
    //	@Ignore
    //	private Integer qty3=0.0;
    //	@Ignore
    //	private Integer qty4=0.0;
    @Ignore
    var qty1 = 0.0

    @Ignore
    var qty2 = 0.0

    @Ignore
    var qty3 = 0.0

    @Ignore
    var qty4 = 0.0

    //	@Ignore
    //	private Integer qty1Kembali=0.0;
    //	@Ignore
    //	private Integer qty2Kembali=0.0;
    //	@Ignore
    //	private Integer qty3Kembali=0.0;
    //	@Ignore
    //	private Integer qty4Kembali=0.0;
    @Ignore
    var qty1Kembali = 0.0

    @Ignore
    var qty2Kembali = 0.0

    @Ignore
    var qty3Kembali = 0.0

    @Ignore
    var qty4Kembali = 0.0

    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    var qty = 0.0

    /*
    * Qty Kembali digunakan jika barang kurang atau Rusak
    */
    //	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
    //	private Integer qtyKembali = 0;	
    //	@Ignore
    //	private Integer qtyNET=0.0;
    var qtyKembali = 0.0

    @Ignore
    var qtyNET = 0.0

    //Subtotal sebelum disc
    @Ignore
    var subtotalRp = 0.0

    @Ignore
    var tempString = ""

    @Ignore
    var tempInt = 0

    @Ignore
    var tempFloat = 0.0

    @Ignore
    var tempDouble31 = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftStockTransferhBean", referencedColumnName="refno")
    //	private FtStockTransferh ftStockTransferhBean;
    var ftStockTransferhBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0
}