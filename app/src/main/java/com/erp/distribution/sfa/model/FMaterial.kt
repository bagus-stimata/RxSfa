package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumUom
import org.codehaus.jackson.annotate.JsonIgnore
import java.util.*

//@Entity(tableName = "fmaterial")
@Entity(tableName = "fMaterial")
class FMaterial  (
    @PrimaryKey
    var id : Int,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
//    @Ignore
//    var sourceID : Int,

//    @Ignore
//    var noUrut : Int,

    var pcode : String,
    var barcode : String,
    var pname : String,

//    @Ignore
//    var oldKode1 : String,

//    @Ignore
//    var varianName : String,

//    @Ignore
//    var isFreeGood : Boolean,

//    @Ignore
//    var shortname : String,
    var isStatusActive : Boolean,

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
//    @Ignore
//    var isExclusiveDivisionTransaction : Boolean,

//    @Ignore
//    var isExclusiveDivisionView : Boolean,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean : Int,

    /*
    * TAX
    */
    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    var ftaxBean : Int,

//    @Ignore
//    var isTaxable : Boolean,

    /*
    * Adalah Vendor Utama Produk Tersebut
    * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
    * 
    * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
    * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
    */
//    @Ignore
//    var isExclusiveVendorTransaction : Boolean,

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    var fvendorBean : Int,

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean_Utm;
//    @Ignore
//    var fwarehouseBean_Utm : Int,

//    @Ignore
//    var materialType: EnumMaterialType,

    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
//    @Ignore
//    var fdistributionChannelBean : Int,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3 fmaterialGroup3Bean;
    var fmaterialGroup3Bean : Int,

    /*
    * KLASIFIKASI: SALES
    */
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
    //	private FMaterialSalesBrand fmaterialSalesBrandBean;
    var fmaterialSalesBrandBean : Int,

    //BATCH CODE --> Berhubungan dengan Stockist atau Gudang
    //PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
//    @Ignore
//    var principalCode : String,

//    @Ignore
//    var batchCode : String,

//    @Ignore
//    var productionCode : String,
    var productionDate : Date? = Date(),
    var expiredDate : Date = Date(),

//    @Ignore
//    var prodclass : Int,
    var uom1 : String,
    var uom2 : String,
    var uom3 : String,
    var uom4 : String,
    var convfact1 : Int, //uom1 to uom4
    var convfact2 : Int, //uom2 to uom4
    var convfact3  : Int, //uom3 to uom4

    /*
    * PRICE yang muncul pada faktur dengan menggunakan UOM
    * 0. dan 1 adalah default
    * 2. uom 2
    * 3. uom 3
    * 4.uom 4
    * 
    */
    var priceUom: EnumUom? = EnumUom.UOM1,

    //	@Transient
    //	private Integer temp_QtySaldo : Int,; //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur
//    @Ignore
//    var temp_QtySaldo : Int, //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur

    /*
    * Harga Beli berbeda dengan HPP
    */
//    @Ignore
//    var hargaBeliUOM4NetAfterPpn : Int,

    /*
    * HPP disimpan Perdivisi pada Tabel AccBalanceHpp
    * HPP adalah Harga Net Per Barang SEBELUM PPN
    */
//    @Ignore
//    var hppAwalPprice2 : Int, //Jika tidak ada HPP Awal maka menggunakan Harga Barang Net Sebelum PPN

//    @Ignore
//    var hppLifo : Int,

//    @Ignore
//    var hppLifoTotalAmount : Int,

//    @Ignore
//    var hppAverage : Int,

//    @Ignore
//    var hppAverageTotalAmount : Int,

//    @Ignore
//    var hppFifo : Int,

//    @Ignore
//    var hppFifoTotalAmount : Int,

    //PPRICE:: Disimpan dalam satuan Terbesar dan Terkecil. Setelah dan sebelum PPN
    var pprice : Double,
    var ppriceAfterPpn : Double,
    var pprice2 : Double,
    var pprice2AfterPpn : Double,
    var sprice : Double,
    var spriceAfterPpn: Double,
    var sprice2 : Double,
    var sprice2AfterPpn : Double,

    /*
    * Min Stok: sama dengan Buffer Stock
    * Max Stock: Stok dikatakan Over, sebetulnya tidak terlalu kepake, karena
    * cara yang paling realistis untuk mengukur stok over adalah
    * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah = Jumlah hari stok akan habis
    */
    var minQtyStok : Int,

    //TIDAK BOLEH DIGANTI-GANTI
//    @Ignore
//    var volumeSmalest : Int,

    //Dalam Grams
    var weightSmalest : Int,

    //Dalam Grams
    var caseWeight : Int,

//    @Ignore
    var isStared: Boolean = false,
//    @Ignore
    var isUnread: Boolean = false,
//    @Ignore
    var isSelected: Boolean = false,


    var created : Date = Date(),
     
    var modified : Date = Date(),

    var modifiedBy : String //User ID

)