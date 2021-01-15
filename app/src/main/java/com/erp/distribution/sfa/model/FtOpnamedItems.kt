package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//@Entity(tableName = "ftopnamed_items")
@Entity(tableName = "FtOpnamedItems")
class FtOpnamedItems {
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

    @Ignore
    var qty1 = 0.0

    @Ignore
    var qty2 = 0.0

    @Ignore
    var qty3 = 0.0

    @Ignore
    var qty4 = 0.0

    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0; //Qty Fisik
    var qty = 0.0 //Qty Fisik

    //	@Column(name="QTY_TEORI", length=9)
    //	private Integer qtyTeori=0.0;	//Qty pada Komputer
    var qtyTeori = 0.0 //Qty pada Komputer

    @Ignore
    var qty1_Adjust = 0.0

    @Ignore
    var qty2_Adjust = 0.0

    @Ignore
    var qty3_Adjust = 0.0

    @Ignore
    var qty4_Adjust = 0.0

    //	private Integer qtyAdjust=0.0; //Qty setelah dihitung dan dilakukan penyesuaian
    var qtyAdjust = 0.0 //Qty setelah dihitung dan dilakukan penyesuaian
    var isVisible = true

    //Subtotal sebelum disc
    @Ignore
    var subtotalRp = 0.0

    @Ignore
    var subtotalTeoriRp = 0.0

    @Ignore
    var subtotalAdjustRp = 0.0

    @Ignore
    var tempString = ""

    @Ignore
    var tempInt = 0

    @Ignore
    var tempFloat = 0.0

    @Ignore
    var tempDouble31 = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftOpnamehBean", referencedColumnName="refno")
    //	private FtOpnameh ftOpnamehBean;
    var ftOpnamehBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0
}