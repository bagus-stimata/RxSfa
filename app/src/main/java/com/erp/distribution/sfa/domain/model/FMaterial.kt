package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.data.source.entity.FMaterialEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumUom
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.DateFormat
import java.util.*

@Parcelize
data class FMaterial  (
    var id : Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
//    @Ignore
//    var sourceID : Int =0,

//    @Ignore
//    var noUrut : Int =0,

    var pcode : String ="",
    var barcode : String ="",
    var pname : String ="",

    var isStatusActive : Boolean =false,

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
    //	private FDivision fdivisionBean;
//    var fdivisionBean : Int =0,
    var fdivisionBean : FDivision = FDivision(),

    /*
    * TAX
    */
    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    var ftaxBean : Int? =0,

//    @Ignore
    var isTaxable : Boolean =false,

    /*
    * Adalah Vendor Utama Produk Tersebut
    * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
    * 
    * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
    * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
    */
//    @Ignore
//    var isExclusiveVendorTransaction : Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
//    var fvendorBean : Int =0,
    var fvendorBean : FVendor? = FVendor(),

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean_Utm;
//    @Ignore
//    var fwarehouseBean_Utm : Int =0,

//    @Ignore
//    var materialType: EnumMaterialType,

    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
//    @Ignore
//    var fdistributionChannelBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
//    var FMaterialGroup3 fmaterialGroup3Bean;
//    var fmaterialGroup3Bean : Int =0,
    var fmaterialGroup3Bean : FMaterialGroup3 = FMaterialGroup3(),

    /*
    * KLASIFIKASI: SALES
    */
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
    //	private FMaterialSalesBrand fmaterialSalesBrandBean;
    var fmaterialSalesBrandBean : Int =0,

    var ftSaleshdItemsSet : List<FtSalesdItems> = listOf<FtSalesdItems>(),


        //BATCH CODE --> Berhubungan dengan Stockist atau Gudang
    //PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
//    @Ignore
//    var principalCode : String ="",

//    @Ignore
//    var batchCode : String ="",

//    @Ignore
//    var productionCode : String ="",
    var productionDate : Date? = Date(),
    var expiredDate : Date = Date(),

//    @Ignore
//    var prodclass : Int =0,
    var uom1 : String ="",
    var uom2 : String ="",
    var uom3 : String ="",
    var uom4 : String ="",
    var convfact1 : Int =0, //uom1 to uom4
    var convfact2 : Int =0, //uom2 to uom4
    var convfact3  : Int =0, //uom3 to uom4

    /*
    * PRICE yang muncul pada faktur dengan menggunakan UOM
    * 0. dan 1 adalah default
    * 2. uom 2
    * 3. uom 3
    * 4.uom 4
    * 
    */
    var priceUom: EnumUom? = EnumUom.UOM1,


    //PPRICE:: Disimpan dalam satuan Terbesar dan Terkecil. Setelah dan sebelum PPN
    var pprice : Double =0.0,
    var ppriceAfterPpn : Double =0.0,
    var pprice2 : Double =0.0,
    var pprice2AfterPpn : Double =0.0,
    var sprice : Double =0.0,
    var spriceAfterPpn: Double =0.0,
    var sprice2 : Double =0.0,
    var sprice2AfterPpn : Double =0.0,

    /*
    * Min Stok: sama dengan Buffer Stock
    * Max Stock: Stok dikatakan Over, sebetulnya tidak terlalu kepake, karena
    * cara yang paling realistis untuk mengukur stok over adalah
    * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah = Jumlah hari stok akan habis
    */
    var minQtyStok : Int =0,
        

    //Dalam Grams
    var weightSmalest : Int =0,

    //Dalam Grams
    var caseWeight : Int =0,

    var stared: Boolean? = false,
    var unread: Boolean? = false,
    var selected: Boolean? = false,


    var created : Date = Date(),
     
    var modified : Date = Date(),

    var modifiedBy : String ="" //User ID

): Model(), Parcelable, Serializable {
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, thePcode: String, thePname: String): this(id = theId, pcode=thePcode, pname = thePname)


    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}


internal fun FMaterial.toEntity(): FMaterialEntity {
    return FMaterialEntity(
            id = id,
            pcode = pcode,
            pname = pname,
            uom1 = uom1,
            uom2 = uom2,
            uom3 = uom3,
            uom4 = uom4,
            convfact1 = convfact1,
            convfact2 = convfact2,
            convfact3 = convfact3,
            pprice = pprice,
            pprice2 = pprice2,
            ppriceAfterPpn = ppriceAfterPpn,
            pprice2AfterPpn = pprice2AfterPpn,
            sprice = sprice,
            sprice2 = sprice2,
            spriceAfterPpn = spriceAfterPpn,
            sprice2AfterPpn = sprice2AfterPpn,

            isStatusActive = isStatusActive,
            stared = stared,
            selected = selected,
            unread = unread,

            fmaterialSalesBrandBean = fmaterialSalesBrandBean!!,
            ftaxBean = ftaxBean!!,
            isTaxable = isTaxable!!,
            fvendorBean = fvendorBean!!.id,
            fdivisionBean = fdivisionBean.id,

            created = created!!,
            modified = modified!!,
            modifiedBy = modifiedBy!!

    )
}