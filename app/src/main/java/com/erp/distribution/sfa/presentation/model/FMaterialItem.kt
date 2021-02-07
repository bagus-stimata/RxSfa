package com.erp.distribution.sfa.presentation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumUom
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.DateFormat
import java.util.*

@Parcelize
data class FMaterialItem  (
    var id : Int =0,
    var pcode : String ="",
    var barcode : String ="",
    var pname : String ="",
    var isStatusActive : Boolean =false,

    var fdivisionBean : FDivisionItem?,
    var ftaxBean : Int =0,

//    var fvendorBean :  =0,

//    @Ignore
//    var materialType: EnumMaterialType,

    //	private FDistributionChannel fdistributionChannelBean;
//    @Ignore
//    var fdistributionChannelBean : Int =0,

//    var FMaterialGroup3 fmaterialGroup3Bean;
//    var fmaterialGroup3Bean : Int =0,
    var fmaterialGroup3Bean : FMaterialGroup3Item= FMaterialGroup3Item(),

    /*
    * KLASIFIKASI: SALES
    */
    //	private FMaterialSalesBrand fmaterialSalesBrandBean;
    var fmaterialSalesBrandBean : Int =0,

//    var ftSaleshdItemsSet : List<FtSalesdItems> = listOf<FtSalesdItems>(),

//    @Ignore
//    var prodclass : Int =0,
    var uom : String ="",
    var convfact : String ="", //uom1 to uom4

    var sprice : Double =0.0,
    var spriceAfterPpn: Double =0.0,
    var sprice2 : Double =0.0,
    var sprice2AfterPpn : Double =0.0,

    var saldoStock : Int =0,

    var stared: Boolean? = false,
    var unread: Boolean? = false,
    var selected: Boolean? = false,


    var created : Date = Date(),
    var modified : Date = Date(),
    var modifiedBy : String ="" //User ID

): Model(), Parcelable, Serializable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}