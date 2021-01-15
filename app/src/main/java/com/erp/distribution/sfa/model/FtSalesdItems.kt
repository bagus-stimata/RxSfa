package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumUom
import java.io.Serializable

//@Entity(tableName = "ftsalesd_items")
@Entity(tableName = "FtSalesdItems")
class FtSalesdItems : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var isFreeGood = false
    var noUrut = 0
    var notes = ""
    var sprice = 0.0

    @Ignore
    var spricePpnRp = 0.0

    /*
    * Dasar harga total
    */
    var isTax = true

    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    var ftaxBean = 0
    var taxPercent = 0.0

    /*
    * End: Dasar harga total
    */
    @Ignore
    var spriceUom2 = 0.0

    @Ignore
    var spriceUom3 = 0.0

    @Ignore
    var spriceUom4 = 0.0

    @Ignore
    var spriceAfterPpn = 0.0

    @Ignore
    var spriceUom2AfterPpn = 0.0

    @Ignore
    var spriceUom3AfterPpn = 0.0

    @Ignore
    var spriceUom4AfterPpn = 0.0

    //HARGA NET SETELAH Diskon Barang Semua: Ditaruh dibelakang diskon Harusnya
    @Ignore
    var spriceNET_Uom1AfterDiscAfterPpn = 0.0

    @Ignore
    var spriceNET_Uom2AfterDiscAfterPpn = 0.0

    @Ignore
    var spriceNET_Uom3AfterDiscAfterPpn = 0.0

    @Ignore
    var spriceNET_Uom4AfterDiscAfterPpn = 0.0

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

    /*Setiap pengiriman ada 2 kemungkinan: (1)Coretan Faktur/Tolakan dan (2)Faktur Batal
   qty = qtyTerkirim
   Total Jumlah yang diOrder = qty + qtyKembali
   */
    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    //	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
    //	private Integer qtyKembali = 0;	
    //	@Ignore
    //	private Integer qtyNET=0.0;
    var qty = 0.0
    var qtyKembali = 0.0

    @Ignore
    var qtyNET = 0.0

    /*
    * Untuk Keperluan Retur
    */
    //	@Column(name="QTYRETURN", length=9)
    //	private Integer qtyReturn =0.0;
    var qtyReturn = 0.0

    /*
    * PRICE yang muncul pada faktur dengan menggunakan UOM
    * 0. dan 1 adalah default
    * 2. uom 2
    * 3. uom 3
    * 4.uom 4
    * 
    */
    var priceUom = EnumUom.UOM1

    //Sub total sebelum diskon
    @Ignore
    var subtotalRp = 0.0

    @Ignore
    var subtotalPpnRp = 0.0

    @Ignore
    var subtotalRpAfterPpn = 0.0
    var disc1 = 0.0

    @Ignore
    var disc1Rp = 0.0

    @Ignore
    var disc1PpnRp = 0.0

    @Ignore
    var disc1RpAfterPpn = 0.0

    @Ignore
    var disc1RpAfterPpnUom1 = 0.0

    @Ignore
    var disc1RpAfterPpnUom2 = 0.0

    @Ignore
    var disc1RpAfterPpnUom3 = 0.0

    @Ignore
    var disc1RpAfterPpnUom4 = 0.0
    var disc2: Double? = null

    @Ignore
    var disc2Rp = 0.0

    @Ignore
    var disc2PpnRp = 0.0

    @Ignore
    var disc2RpAfterPpn = 0.0

    @Ignore
    var disc2RpAfterPpnUom1 = 0.0

    @Ignore
    var disc2RpAfterPpnUom2 = 0.0

    @Ignore
    var disc2RpAfterPpnUom3 = 0.0

    @Ignore
    var disc2RpAfterPpnUom4 = 0.0
    var disc3 = 0.0

    @Ignore
    var disc3Rp = 0.0

    @Ignore
    var disc3PpnRp = 0.0

    @Ignore
    var disc3RpAfterPpn = 0.0

    @Ignore
    var disc3RpAfterPpnUom1 = 0.0

    @Ignore
    var disc3RpAfterPpnUom2 = 0.0

    @Ignore
    var disc3RpAfterPpnUom3 = 0.0

    @Ignore
    var disc3RpAfterPpnUom4 = 0.0

    @Ignore
    var subtotalAfterDisc123Rp = 0.0

    @Ignore
    var subtotalAfterDisc123PpnRp = 0.0

    @Ignore
    var subtotalAfterDisc123RpAfterPpn = 0.0
    var disc1Plus = 0.0

    @Ignore
    var disc1PlusRp = 0.0

    @Ignore
    var disc1PlusPpnRp = 0.0

    @Ignore
    var disc1PlusRpAfterPpn = 0.0

    @Ignore
    var disc1PlusRpAfterPpnUom1 = 0.0

    @Ignore
    var disc1PlusRpAfterPpnUom2 = 0.0

    @Ignore
    var disc1PlusRpAfterPpnUom3 = 0.0

    @Ignore
    var disc1PlusRpAfterPpnUom4 = 0.0

    @Ignore
    var subtotalAfterDisc1PlusRp = 0.0

    @Ignore
    var subtotalAfterDisc1PlusPpnRp = 0.0

    @Ignore
    var subtotalAfterDisc1PlusRpAfterPpn = 0.0
    var disc2Plus = 0.0

    @Ignore
    var disc2PlusRp = 0.0

    @Ignore
    var disc2PlusPpnRp = 0.0 //ppn

    @Ignore
    var disc2PlusRpAfterPpn = 0.0

    @Ignore
    var disc2PlusRpAfterPpnUom1 = 0.0

    @Ignore
    var disc2PlusRpAfterPpnUom2 = 0.0

    @Ignore
    var disc2PlusRpAfterPpnUom3 = 0.0

    @Ignore
    var disc2PlusRpAfterPpnUom4 = 0.0

    @Ignore
    var subtotalAfterDisc2PlusRp = 0.0

    @Ignore
    var subtotalAfterDisc2PlusPpnRp = 0.0 //ppn

    @Ignore
    var subtotalAfterDisc2PlusRpAfterPpn = 0.0

    /*
    * AFTER DISKON NOTA :: TAMBAHAN UNTUK MEMUDAHKAN PERHITUNGAN
    */
    @Ignore
    var discNota1 = 0.0

    @Ignore
    var discNota1Rp = 0.0

    @Ignore
    var discNota1PpnRp = 0.0 //ppn

    @Ignore
    var discNota1RpAfterPpn = 0.0

    /*
    * TAMBAHAN
    */
    @Ignore
    var subtotalAfterDiscNota1Rp = 0.0

    @Ignore
    var subtotalAfterDiscNota1PpnRp = 0.0 //ppn

    @Ignore
    var subtotalAfterDiscNota1RpAfterPpn = 0.0

    @Ignore
    var discNota2 = 0.0

    @Ignore
    var discNota2Rp = 0.0

    @Ignore
    var discNota2PpnRp = 0.0 //ppn

    @Ignore
    var discNota2RpAfterPpn = 0.0

    @Ignore
    var subtotalAfterDiscNota2Rp = 0.0

    @Ignore
    var subtotalAfterDiscNota2PpnRp = 0.0 //ppn

    @Ignore
    var subtotalAfterDiscNota2RpAfterPpn = 0.0

    @Ignore
    var discNotaPlus_FG = 0.0

    @Ignore
    var discNotaPlusRp_FG = 0.0

    @Ignore
    var discNotaPlusPpnRp_FG = 0.0 //ppn

    @Ignore
    var discNotaPlusRpAfterPpn_FG = 0.0

    @Ignore
    var subtotalAfterDiscNotaPlusRp_FG = 0.0

    @Ignore
    var subtotalAfterDiscNotaPlusPpnRp_FG = 0.0 //ppn

    @Ignore
    var subtotalAfterDiscNotaPlusRpAfterPpn_FG = 0.0

    @Ignore
    var tempString = ""

    @Ignore
    var tempInt = 0

    @Ignore
    var tempDouble1 = 0.0

    @Ignore
    var tempDouble2 = 0.0

    @Ignore
    var tempDouble31 = 0

    //TPR BARANG DAN UANG
    //	@Column(name="TPRB")
    //	private Double tprb=0.0;
    //	@Column(name="TPRU_DISC")
    //	private Double tpruDisc=0.0;
    //	@Column(name="TPRU_CASHBACK")
    //	private Double tpruCashback=0.0;
    //	@ManyToOne
    //	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
    //	private FtSalesh ftSaleshBean;
    var ftSaleshBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0
}