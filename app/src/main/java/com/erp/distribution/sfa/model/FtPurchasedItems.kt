package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumUom
import java.io.Serializable

//@Entity(tableName = "ftpurchased_items")
@Entity(tableName = "FtPurchasedItems")
class FtPurchasedItems : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var noUrut = 0

    /*
    * IN PURCHASE REQUESITION TAX USED : APPROVE
    */
    var isFreeGood = false

    /*
    * EXCLUDE COGS or No Cogs
    * Jika True maka tidak dihitung sebagai HPP/COGS dan Defaultnya adalah Dihitung sebagai COGS
    * Syarat: "FreeGood = true" (hanya untuk barang free good)
    * Jika Exclude COGS maka nilainya akan secara langsung di jurnal menjadi Jurnal Memorial
    * 
    * TIDAK JADI
    * 
    */
    //	@Column(name="EXCL_COGS")
    //	private boolean exclCogs=false;
    var notes = ""

    /*
    * Dasar harga total
    */
    var pprice = 0.0 //Harga disimpan dalam Satuan Besar Sebelum Ppn

    //	@Ignore
    var ppricePpnRp = 0.0

    /*
    * IN PURCHASE REQUESITION TAX USED TO : REJECT
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
    //	@Ignore
    var ppriceUom2 = 0.0

    //	@Ignore
    var ppriceUom3 = 0.0

    //	@Ignore
    var ppriceUom4 = 0.0

    //HARGA BELI SEBELUM
    //	@Ignore
    var ppriceOldAfterPpn = 0.0 //sama dengan ppriceUom1OldAfterPpn

    //	@Ignore
    var ppriceUom2OldAfterPpn = 0.0

    //	@Ignore
    var ppriceUom3OldAfterPpn = 0.0

    //	@Ignore
    var ppriceUom4OldAfterPpn = 0.0

    //	@Ignore
    var selisihHargaBeliLama = ""

    //	@Ignore
    var ppriceAfterPpn = 0.0

    //	@Ignore
    var ppriceUom2AfterPpn = 0.0

    //	@Ignore
    var ppriceUom3AfterPpn = 0.0

    //	@Ignore
    var ppriceUom4AfterPpn = 0.0

    //HARGA NET SETELAH Diskon Barang Semua: Ditaruh dibelakang diskon Harusnya
    //	@Ignore
    var ppriceNET_Uom1AfterDiscAfterPpn = 0.0

    //	@Ignore
    var ppriceNET_Uom2AfterDiscAfterPpn = 0.0

    //	@Ignore
    var ppriceNET_Uom3AfterDiscAfterPpn = 0.0

    //	@Ignore
    var ppriceNET_Uom4AfterDiscAfterPpn = 0.0

    //	@Ignore
    //	private Integer qty1=0.0;
    //	@Ignore
    //	private Integer qty2=0.0;
    //	@Ignore
    //	private Integer qty3=0.0;
    //	@Ignore
    //	private Integer qty4=0.0;
    //	@Ignore
    var qty1 = 0.0

    //	@Ignore
    var qty2 = 0.0

    //	@Ignore
    var qty3 = 0.0

    //	@Ignore
    var qty4 = 0.0

    //	@Ignore
    var qty1Kembali = 0.0

    //	@Ignore
    var qty2Kembali = 0.0

    //	@Ignore
    var qty3Kembali = 0.0

    //	@Ignore
    var qty4Kembali = 0.0

    /*Setiap pengiriman ada 2 kemungkinan: (1)Coretan Faktur/Tolakan dan (2)Faktur Batal
   */
    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    var qty = 0.0
    var qtyKembali = 0.0

    //	@Ignore
    var qtyNET = 0.0

    //	@Column(name="QTY_RETURN", length=9) //Dipakai untuk Algoritma Return: Berbeda dengan kegunaan dari FtSalesditems
    //	private Integer qtyReturn = 0;
    var qtyReturn = 0.0

    //	@Column(name="QTY_USED_BYCHILD", length=9)
    //	private Integer qty_usedByChild =0.0;
    var qty_usedByChild = 0.0

    /*
    * PRICE yang muncul pada faktur dengan menggunakan UOM
    * 0. dan 1 adalah default
    * 2. uom 2
    * 3. uom 3
    * 4.uom 4
    * 
    */
    var priceUom = EnumUom.UOM1

    //Subtotal sebelum disc
    //	@Ignore
    var subtotalRp = 0.0

    //	@Ignore
    var subtotalPpnRp = 0.0

    //	@Ignore
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
    var subtotalAfterDiscNota12Rp = 0.0

    @Ignore
    var subtotalAfterDiscNota12PpnRp = 0.0 //ppn

    @Ignore
    var subtotalAfterDiscNota12RpAfterPpn = 0.0

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
    var discNotaExclCogs = 0.0

    @Ignore
    var discNotaExclCogsRp = 0.0

    @Ignore
    var discNotaExclCogsPpnRp = 0.0 //ppn

    @Ignore
    var discNotaExclCogsRpAfterPpn = 0.0

    @Ignore
    var subtotalAfterDiscNotaExclCogsRp = 0.0

    @Ignore
    var subtotalAfterDiscNotaExclCogsPpnRp = 0.0 //ppn

    @Ignore
    var subtotalAfterDiscNotaExclCogsRpAfterPpn = 0.0

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

    //	@ManyToOne
    //	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
    //	private FtPurchaseh ftPurchasehBean;
    var ftPurchasehBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0
}