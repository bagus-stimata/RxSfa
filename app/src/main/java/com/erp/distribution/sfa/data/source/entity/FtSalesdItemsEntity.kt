package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.*
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumUom
import com.erp.distribution.sfa.domain.model.FMaterial
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "ftSalesdItems")
@Parcelize
class FtSalesdItemsEntity (
    @PrimaryKey(autoGenerate =true)
    var id: Long =0,
    var isFreeGood : Boolean =false,
    var noUrut : Int =0,
    var notes : String ="",
    var sprice : Double =0.0,

//    @Ignore
//    var spricePpnRp : Double =0.0,

    /*
    * Dasar harga total
    */
    var isTax : Boolean =true,

    //	@ManyToOne
    //	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
    //	private FTax ftaxBean;
    var ftaxBean : Int =0,
    var taxPercent : Double =0.0,

    /*
    * End: Dasar harga total
    */
//    @Ignore
//    var spriceUom2 : Double =0.0,

//    @Ignore
//    var spriceUom3 : Double =0.0,

//    @Ignore
//    var spriceUom4 : Double =0.0,

//    @Ignore
//    var spriceAfterPpn : Double =0.0,

//    @Ignore
//    var spriceUom2AfterPpn : Double =0.0,

//    @Ignore
//    var spriceUom3AfterPpn : Double =0.0,

//    @Ignore
//    var spriceUom4AfterPpn : Double =0.0,

    //HARGA NET SETELAH Diskon Barang Semua: Ditaruh dibelakang diskon Harusnya
//    @Ignore
//    var spriceNET_Uom1AfterDiscAfterPpn : Double =0.0,

//    @Ignore
//    var spriceNET_Uom2AfterDiscAfterPpn : Double =0.0,

//    @Ignore
//    var spriceNET_Uom3AfterDiscAfterPpn : Double =0.0,

//    @Ignore
//    var spriceNET_Uom4AfterDiscAfterPpn : Double =0.0,

    //	@Ignore
    //	private Integer qty1=0.0;
    //	@Ignore
    //	private Integer qty2=0.0;
    //	@Ignore
    //	private Integer qty3=0.0;
    //	@Ignore
    //	private Integer qty4=0.0;
//    @Ignore
//    var qty1 : Double =0.0,

//    @Ignore
//    var qty2 : Double =0.0,

//    @Ignore
//    var qty3 : Double =0.0,

//    @Ignore
//    var qty4 : Double =0.0,

    //	@Ignore
    //	private Integer qty1Kembali=0.0;
    //	@Ignore
    //	private Integer qty2Kembali=0.0;
    //	@Ignore
    //	private Integer qty3Kembali=0.0;
    //	@Ignore
    //	private Integer qty4Kembali=0.0;
//    @Ignore
//    var qty1Kembali : Double =0.0,

//    @Ignore
//    var qty2Kembali : Double =0.0,

//    @Ignore
//    var qty3Kembali : Double =0.0,

//    @Ignore
//    var qty4Kembali : Double =0.0,

    /*Setiap pengiriman ada 2 kemungkinan: (1)Coretan Faktur/Tolakan dan (2)Faktur Batal
   qty = qtyTerkirim
   Total Jumlah yang diOrder = qty + qtyKembali
   */
    //	@Column(name="QTY", length=9)
    //	private Integer qty=0.0;
    //	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
    //	private Integer qtyKembali : Int =0,;	
    //	@Ignore
    //	private Integer qtyNET=0.0;
    var qty : Double =0.0,
    var qtyKembali : Double =0.0,

//    @Ignore
//    var qtyNET : Double =0.0,

    /*
    * Untuk Keperluan Retur
    */
    //	@Column(name="QTYRETURN", length=9)
    //	private Integer qtyReturn =0.0;
    var qtyReturn : Double =0.0,

    /*
    * PRICE yang muncul pada faktur dengan menggunakan UOM
    * 0. dan 1 adalah default
    * 2. uom 2
    * 3. uom 3
    * 4.uom 4
    * 
    */
    var priceUom: EnumUom? = EnumUom.UOM1,

    //Sub total sebelum diskon
//    @Ignore
//    var subtotalRp : Double =0.0,

//    @Ignore
//    var subtotalPpnRp : Double =0.0,

//    @Ignore
//    var subtotalRpAfterPpn : Double =0.0,
    var disc1 : Double =0.0,

//    @Ignore
//    var disc1Rp : Double =0.0,

//    @Ignore
//    var disc1PpnRp : Double =0.0,

//    @Ignore
//    var disc1RpAfterPpn : Double =0.0,

//    @Ignore
//    var disc1RpAfterPpnUom1 : Double =0.0,

//    @Ignore
//    var disc1RpAfterPpnUom2 : Double =0.0,

//    @Ignore
//    var disc1RpAfterPpnUom3 : Double =0.0,

//    @Ignore
//    var disc1RpAfterPpnUom4 : Double =0.0,
    var disc2: Double = 0.0,
//
//    @Ignore
//    var disc2Rp : Double =0.0,
//
//    @Ignore
//    var disc2PpnRp : Double =0.0,
//
//    @Ignore
//    var disc2RpAfterPpn : Double =0.0,
//
//    @Ignore
//    var disc2RpAfterPpnUom1 : Double =0.0,
//
//    @Ignore
//    var disc2RpAfterPpnUom2 : Double =0.0,
//
//    @Ignore
//    var disc2RpAfterPpnUom3 : Double =0.0,
//
//    @Ignore
//    var disc2RpAfterPpnUom4 : Double =0.0,
    var disc3 : Double =0.0,

//    @Ignore
//    var disc3Rp : Double =0.0,
//
//    @Ignore
//    var disc3PpnRp : Double =0.0,
//
//    @Ignore
//    var disc3RpAfterPpn : Double =0.0,
//
//    @Ignore
//    var disc3RpAfterPpnUom1 : Double =0.0,
//
//    @Ignore
//    var disc3RpAfterPpnUom2 : Double =0.0,
//
//    @Ignore
//    var disc3RpAfterPpnUom3 : Double =0.0,
//
//    @Ignore
//    var disc3RpAfterPpnUom4 : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDisc123Rp : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDisc123PpnRp : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDisc123RpAfterPpn : Double =0.0,
    var disc1Plus : Double =0.0,

//    @Ignore
//    var disc1PlusRp : Double =0.0,
//
//    @Ignore
//    var disc1PlusPpnRp : Double =0.0,
//
//    @Ignore
//    var disc1PlusRpAfterPpn : Double =0.0,
//
//    @Ignore
//    var disc1PlusRpAfterPpnUom1 : Double =0.0,
//
//    @Ignore
//    var disc1PlusRpAfterPpnUom2 : Double =0.0,
//
//    @Ignore
//    var disc1PlusRpAfterPpnUom3 : Double =0.0,
//
//    @Ignore
//    var disc1PlusRpAfterPpnUom4 : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDisc1PlusRp : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDisc1PlusPpnRp : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDisc1PlusRpAfterPpn : Double =0.0,
    var disc2Plus : Double =0.0,

//    @Ignore
//    var disc2PlusRp : Double =0.0,
//
//    @Ignore
//    var disc2PlusPpnRp : Double =0.0, //ppn
//
//    @Ignore
//    var disc2PlusRpAfterPpn : Double =0.0,
//
//    @Ignore
//    var disc2PlusRpAfterPpnUom1 : Double =0.0,
//
//    @Ignore
//    var disc2PlusRpAfterPpnUom2 : Double =0.0,
//
//    @Ignore
//    var disc2PlusRpAfterPpnUom3 : Double =0.0,
//
//    @Ignore
//    var disc2PlusRpAfterPpnUom4 : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDisc2PlusRp : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDisc2PlusPpnRp : Double =0.0, //ppn
//
//    @Ignore
//    var subtotalAfterDisc2PlusRpAfterPpn : Double =0.0,
//
//    /*
//    * AFTER DISKON NOTA :: TAMBAHAN UNTUK MEMUDAHKAN PERHITUNGAN
//    */
//    @Ignore
//    var discNota1 : Double =0.0,
//
//    @Ignore
//    var discNota1Rp : Double =0.0,
//
//    @Ignore
//    var discNota1PpnRp : Double =0.0, //ppn
//
//    @Ignore
//    var discNota1RpAfterPpn : Double =0.0,
//
//    /*
//    * TAMBAHAN
//    */
//    @Ignore
//    var subtotalAfterDiscNota1Rp : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDiscNota1PpnRp : Double =0.0, //ppn
//
//    @Ignore
//    var subtotalAfterDiscNota1RpAfterPpn : Double =0.0,
//
//    @Ignore
//    var discNota2 : Double =0.0,
//
//    @Ignore
//    var discNota2Rp : Double =0.0,
//
//    @Ignore
//    var discNota2PpnRp : Double =0.0, //ppn
//
//    @Ignore
//    var discNota2RpAfterPpn : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDiscNota2Rp : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDiscNota2PpnRp : Double =0.0, //ppn
//
//    @Ignore
//    var subtotalAfterDiscNota2RpAfterPpn : Double =0.0,
//
//    @Ignore
//    var discNotaPlus_FG : Double =0.0,
//
//    @Ignore
//    var discNotaPlusRp_FG : Double =0.0,
//
//    @Ignore
//    var discNotaPlusPpnRp_FG : Double =0.0, //ppn
//
//    @Ignore
//    var discNotaPlusRpAfterPpn_FG : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDiscNotaPlusRp_FG : Double =0.0,
//
//    @Ignore
//    var subtotalAfterDiscNotaPlusPpnRp_FG : Double =0.0, //ppn
//
//    @Ignore
//    var subtotalAfterDiscNotaPlusRpAfterPpn_FG : Double =0.0,
//
//    @Ignore
//    var tempString : String ="",
//
//    @Ignore
//    var tempInt : Int =0,
//
//    @Ignore
//    var tempDouble1 : Double =0.0,
//
//    @Ignore
//    var tempDouble2 : Double =0.0,
//
//    @Ignore
//    var tempDouble31 : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var ftSaleshBean : Long =0,
    var fmaterialBean : Int =0

): Serializable, ModelEntity(), Parcelable

internal fun FtSalesdItemsEntity.toDomain(): FtSalesdItems {
    return FtSalesdItems(
        id = id,
        isFreeGood = isFreeGood,
        noUrut = noUrut,
        notes = notes,
        sprice = sprice,

        isTax = isTax,
        ftaxBean = ftaxBean,
        taxPercent = taxPercent,

        qty = qty,
        qtyKembali = qtyKembali,

        qtyReturn = qtyReturn,
        priceUom = priceUom,

        ftSaleshBean = FtSalesh(ftSaleshBean),
        fmaterialBean = FMaterial(fmaterialBean),

        disc1 = disc1,
        disc2 = disc2,
        disc3 = disc3,
        disc1Plus = disc1Plus,
        disc2Plus = disc2Plus

    )
}

data class FtSalesdWithFMaterial(
        @Embedded val ftSalesdItemsEntity: FtSalesdItemsEntity,

        @Relation(
                parentColumn = "fmaterialBean",
                entityColumn = "id"
        )
        val fMaterialEntity: FMaterialEntity?
)
