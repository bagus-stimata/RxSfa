package com.erp.distribution.sfa.presentation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumCurrency
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTipePajakCustomer
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTunaiKredit
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.DateFormat
import java.util.*


@Parcelize
data class FCustomerItem(
        var id: Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Int =0,
        var custno: String ="",
        var isOutletActive: Boolean =false,
        var fdivisionBean: FDivisionItem?,

        var custname: String ="",

        var address1: String ="",
        var city1: String ="",
        var state1: String ="",
        var phone1: String ="",
        var postcode: String ="",
        var email: String ="",
        var whatsApp: String ="",
        var isStatusActive: Boolean =false,

        var latitude: Int =0,
        var longitude: Int =0,

    //	private FCustomerGroup fcustomerGroupBean;
        var fcustomerGroupBean: Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fsubAreaBean", referencedColumnName="ID")
    //	private FSubArea fsubAreaBean;
        var fsubAreaBean: Int =0,

    //	private FDistributionChannel fdistributionChannelBean;
        var fdistributionChannelBean: Int =0,

//        var ftSaleshSet : List<FtSalesh> = listOf<FtSalesh>(),


        //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
        var ftPriceAlthBean: Int =0,

        var stared: Boolean? = false,
        var unread: Boolean? = false,
        var selected: Boolean? = false,

        var created : Date = Date(),
        var modified : Date = Date(),
        var modifiedBy: String ="", //User ID

): Model(), Parcelable, Serializable{
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}

