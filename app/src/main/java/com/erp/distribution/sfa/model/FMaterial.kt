package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumMaterialType
import com.erp.distribution.sfa.model.modelenum.EnumUom
import java.util.*

//@Entity(tableName = "fmaterial")
@Entity(tableName = "FMaterial")
class FMaterial  {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    @Ignore
    var sourceID = 0

    @Ignore
    var noUrut = 0
    var pcode = ""
    var barcode = ""
    var pname = ""

    @Ignore
    var oldKode1 = ""

    @Ignore
     
    var varianName = ""

    @Ignore
     
    var isFreeGood = false

    @Ignore
    var shortname = ""
    var isStatusActive = true

    /*
    * KLASIFIKASI: BASIC
    */
    /* 
	 * exclusiveDivisionTransaction: Input Penjualan dan Pembelian akan menolak jika item product berlainan Divisi
	 * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
	 * jika Division = All Division maka exclusiveDivisionView tidak berlaku
	 * 
	 * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
	 * 
	 * 	 Dalam satu Divisi biasanya terdapat beberapa Vendor
	 */
    @Ignore
     
    var isExclusiveDivisionTransaction = false

    @Ignore
     
    var isExclusiveDivisionView = false

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    /*
    * TAX
    */
    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    var ftaxBean = 0

    @Ignore
     
    var isTaxable = true

    /*
    * Adalah Vendor Utama Produk Tersebut
    * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
    * 
    * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
    * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
    */
    @Ignore
     
    var isExclusiveVendorTransaction = false

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    var fvendorBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean_Utm;
    @Ignore
     
    var fwarehouseBean_Utm = 0

    @Ignore
     
    var materialType: EnumMaterialType? = null

    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    @Ignore
     
    var fdistributionChannelBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3 fmaterialGroup3Bean;
    var fmaterialGroup3Bean = 0

    /*
    * KLASIFIKASI: SALES
    */
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
    //	private FMaterialSalesBrand fmaterialSalesBrandBean;
    var fmaterialSalesBrandBean = 0

    //BATCH CODE --> Berhubungan dengan Stockist atau Gudang
    //PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
    @Ignore
     
    var principalCode = ""

    @Ignore
     
    var batchCode = ""

    @Ignore
     
    var productionCode = ""
    var productionDate = Date()
    var expiredDate = Date()

    @Ignore
     
    var prodclass = 0
    var uom1 = ""
    var uom2 = ""
    var uom3 = ""
    var uom4 = ""
    var convfact1 = 1 //uom1 to uom4
    var convfact2 = 1 //uom2 to uom4
    var convfact3 = 1 //uom3 to uom4

    /*
    * PRICE yang muncul pada faktur dengan menggunakan UOM
    * 0. dan 1 adalah default
    * 2. uom 2
    * 3. uom 3
    * 4.uom 4
    * 
    */
    var priceUom: EnumUom = EnumUom.UOM1

    //	@Transient
    //	private Integer temp_QtySaldo = 0; //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur
    @Ignore
    var temp_QtySaldo = 0.0 //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur

    /*
    * Harga Beli berbeda dengan HPP
    */
    @Ignore
     
    var hargaBeliUOM4NetAfterPpn = 0.0

    /*
    * HPP disimpan Perdivisi pada Tabel AccBalanceHpp
    * HPP adalah Harga Net Per Barang SEBELUM PPN
    */
    @Ignore
     
    var hppAwalPprice2 = 0.0 //Jika tidak ada HPP Awal maka menggunakan Harga Barang Net Sebelum PPN

    @Ignore
     
    var hppLifo = 0.0

    @Ignore
     
    var hppLifoTotalAmount = 0.0

    @Ignore
     
    var hppAverage = 0.0

    @Ignore
     
    var hppAverageTotalAmount = 0.0

    @Ignore
     
    var hppFifo = 0.0

    @Ignore
     
    var hppFifoTotalAmount = 0.0

    //PPRICE:: Disimpan dalam satuan Terbesar dan Terkecil. Setelah dan sebelum PPN
    var pprice = 0.0
    var ppriceAfterPpn = 0.0
    var pprice2 = 0.0
    var pprice2AfterPpn = 0.0
    var sprice = 0.0
    var spriceAfterPpn: Double? = null
    var sprice2 = 0.0
    var sprice2AfterPpn = 0.0

    /*
    * Min Stok: sama dengan Buffer Stock
    * Max Stock: Stok dikatakan Over, sebetulnya tidak terlalu kepake, karena
    * cara yang paling realistis untuk mengukur stok over adalah
    * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah = Jumlah hari stok akan habis
    */
    var minQtyStok = 0

    //TIDAK BOLEH DIGANTI-GANTI
    @Ignore
      //dalam Mili Liter
    var volumeSmalest = 0

    //Dalam Grams
    var weightSmalest = 0

    //Dalam Grams
    var caseWeight = 0

    //IN Cm.. Cm3
    @Ignore
     
    var caseWidth = 0 //Panjang

    @Ignore
     
    var caseHeight = 0 //Tinggi

    @Ignore
     
    var caseDepth = 0 //Lebar (dibalik kan kalau english.. hehehe)

    @Ignore
     
    var isFlagNewItem = false

    @Ignore
     
    var isFlagNewPrice = false

    @Ignore
     
    var isUseSpriceAlt = false

    //#PRICEALT1 -- Retail -->ALL AFTER PPN
    //Retail-Besar
    @Ignore
     
    var spriceAltRetailBes = 0.0

    //Retail-Sedang
    @Ignore
     
    var spriceAltRetailSed = 0.0

    //Retail-Kecil
    @Ignore
     
    var spriceAltRetailKec = 0.0

    //#PRICEALT2 --> Grosir 1
    //Grosir-Besar
    @Ignore
     
    var spriceAltGrosir1Bes = 0.0

    //Grosir-Sed
    @Ignore
     
    var spriceAltGrosir1Sed = 0.0

    //Grosir-Kec
    @Ignore
     
    var spriceAltGrosir1Kec = 0.0

    //#PRICEALT3 --> Grosir 2
    //Grosir2-Bes
    //Grosir-Besar
    @Ignore
     
    var spriceAltGrosir2Bes = 0.0

    //Grosir-Sed
    @Ignore
     
    var spriceAltGrosir2Sed = 0.0

    //Grosir-Kec
    @Ignore
     
    var spriceAltGrosir2Kec = 0.0

    //Grosir Quantity
    @Ignore
     
    var spriceAltGrosirQtyMoreEqual1 = 0.0

    @Ignore
     
    var spriceAltGrosirQtyMoreEqual2 = 0.0

    @Ignore
     
    var spriceAltGrosirQtyMoreEqual3 = 0.0

    @Ignore
     
    var spriceAltGrosirQtyMoreEqual4 = 0.0

    @Ignore
     
    var spriceAltGrosirQtyValue1 = 0.0

     
    var spriceAltGrosirQtyValue2 = 0.0

    @Ignore
     
    var spriceAltGrosirQtyValue3 = 0.0

    @Ignore
     
    var spriceAltGrosirQtyValue4 = 0.0

     
    var created = Date()

     
    var modified = Date()

     
    var modifiedBy = "" //User ID

    @Ignore
     
    var isStared = false

    @Ignore
     
    var isUnread = false

    @Ignore
     
    var isSelected = false
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val fMaterial = o as FMaterial
        return id == fMaterial.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "FMaterial{" +
                "id=" + id +
                '}'
    }
}