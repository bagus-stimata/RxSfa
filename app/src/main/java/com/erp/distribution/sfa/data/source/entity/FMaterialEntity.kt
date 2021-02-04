package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.base.EntityMapper
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumUom
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.FMaterialGroup3
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

//@Entity(tableName? = "fmaterial")
@Parcelize
@Entity(tableName = "fMaterial")
data class FMaterialEntity  (
    @PrimaryKey
    var id : Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID? = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
//    @Ignore
//    var sourceID : Int? =0,

//    @Ignore
//    var noUrut : Int? =0,

    var pcode : String ="",
    var barcode : String? ="",
    var pname : String ="",

//    @Ignore
//    var oldKode1 : String? ="",

//    @Ignore
//    var varianName : String? ="",

//    @Ignore
//    var isFreeGood : Boolean? =false,

//    @Ignore
//    var shortname : String? ="",
    var isStatusActive : Boolean =false,

    /*
    * KLASIFIKASI: BASIC
    */
    /* 
	 * exclusiveDivisionTransaction: Input Penjualan dan Pembelian akan menolak jika item product berlainan Divisi
	 * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
	 * jika Division? = All Division maka exclusiveDivisionView tidak berlaku
	 * 
	 * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
	 * 
	 * 	 Dalam satu Divisi biasanya terdapat beberapa Vendor
	 */
//    @Ignore
//    var isExclusiveDivisionTransaction : Boolean? =false,

//    @Ignore
//    var isExclusiveDivisionView : Boolean? =false,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean : Int? =0,

    /*
    * TAX
    */
    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    var ftaxBean : Int? =0,

//    @Ignore
//    var isTaxable : Boolean? =false,

    /*
    * Adalah Vendor Utama Produk Tersebut
    * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
    * 
    * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
    * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
    */
//    @Ignore
//    var isExclusiveVendorTransaction : Boolean? =false,

    //	@ManyToOne
    //	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
    //	private FVendor fvendorBean;
    var fvendorBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean_Utm;
//    @Ignore
//    var fwarehouseBean_Utm : Int? =0,

//    @Ignore
//    var materialType: EnumMaterialType,

    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
//    @Ignore
//    var fdistributionChannelBean : Int? =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
    //	private FMaterialGroup3 fmaterialGroup3Bean;
    var fmaterialGroup3Bean : Int =0,

    /*
    * KLASIFIKASI: SALES
    */
    //	@ManyToOne
    //	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
    //	private FMaterialSalesBrand fmaterialSalesBrandBean;
    var fmaterialSalesBrandBean : Int =0,

    //BATCH CODE --> Berhubungan dengan Stockist atau Gudang
    //PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
//    @Ignore
//    var principalCode : String? ="",

//    @Ignore
//    var batchCode : String? ="",

//    @Ignore
//    var productionCode : String? ="",
    var productionDate : Date? = Date(),
    var expiredDate : Date? = Date(),

//    @Ignore
//    var prodclass : Int? =0,
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
    * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah? = Jumlah hari stok akan habis
    */
    var minQtyStok : Int? =0,
        

    //Dalam Grams
    var weightSmalest : Int? =0,

    //Dalam Grams
    var caseWeight : Int? =0,

    var stared: Boolean? = false,
    var unread: Boolean? = false,
    var selected: Boolean? = false,


    var created : Date? = Date(),
     
    var modified : Date? = Date(),

    var modifiedBy : String? ="" //User ID

): ModelEntity(), Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}

class FMaterialEntityMapper @Inject constructor(
        private val fMaterialGroup3EntityMapper: FMaterialGroup3EntityMapper
): EntityMapper<FMaterial, FMaterialEntity>{
    override fun mapToDomain(entity: FMaterialEntity): FMaterial  = FMaterial(
            id = entity.id,
            pcode = entity.pcode,
            pname = entity.pname,
//            fdivisionBean = entity.fdivisionBean,
//            fmaterialGroup3Bean? = entity.fmaterialGroup3Bean?.let { fMaterialGroup3EntityMapper.mapToDomain(it) }
//            fmaterialGroup3Bean? =   entity.fmaterialGroup3Bean
    )

    override fun mapToEntity(model: FMaterial): FMaterialEntity = FMaterialEntity(
            id = model.id,
            pcode = model.pcode,
            pname = model.pname,
            fdivisionBean = model.fdivisionBean,
//            fmaterialGroup3Bean? =  model.fmaterialGroup3Bean
    )

}


