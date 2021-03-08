package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.erp.distribution.sfa.data.base.ModelEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumCurrency
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTipePajakCustomer
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTunaiKredit
import com.erp.distribution.sfa.domain.model.*
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.util.*



@Entity(tableName= "fCustomer")
@Parcelize
data class FCustomerEntity(
    @PrimaryKey
    var id: Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID? = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var custno: String ="",
    var isOutletActive: Boolean =false,
    var isFlagNewItem: Boolean? =false,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean: Int =0,

    /*
    * Fungsinya: Jika menarik data dari sistem lain dimana mempunyai kode customer yang berbeda
    * Maka akan di konversikan menjadi kode pada sistem
    * Pada import data: ada opsi pilih mapping customer yang mana
    */

    /*
    * Digunanakan untuk menghasilkan kode yang berbeda jika di extract
    * Fungsinya: Untuk Integrasi dengan Sistem Lain jika ternyata kode customer berbeda
    */
    var custname: String ="",

    /*
    * PERPAJAKAN / TAX
    */
    var isPkp: Boolean? =false,
    var namaPrshFakturPajak: String? ="",
    var alamatPrshFakturPajak: String? ="",
    var npwp: String? ="",
    var tanggalPengukuhanPkp : Date? = Date(),
    var tunaikredit: EnumTunaiKredit? = EnumTunaiKredit.T,
    var lamaCredit: Int =0,
    var creditlimit: Int =0,
    var maxInvoice: Int =0,
    var namaPemilik: String? ="",
    var address1: String ="",
    var address2: String ="",
    var city1: String ="",
    var state1: String ="",
    var phone1: String? ="",
    var postcode: String? ="",
    var email: String? ="",
    var whatsApp: String? ="",
    var isStatusActive: Boolean? =false,

    var isNoeffcall: Boolean? =false,
    var latitude: Int? =0,
    var longitude: Int? =0,

    /*
    * sementara belum dipakai sampai tahu principal atau SAP
    */
    //SHIPTO BILLTO	
    //	@Column(name="SHIPTOBILLTO", length=20)
    //	private String shipToBillTo? ="";
    //	@Column(name="BILLTO", length=20)
    //	private String billTo? ="";
    var basicDisc1Barang: Int? =0,
    var basicDisc1PlusBarang: Int? =0,
    var isDisc1RegManual: Boolean? =false,
    var isDiscPlusRegManual: Boolean? =false,

    /*
    * 0? = Menggunakan Harga Reguler Distributor (tidak mengenal harga bertingkat)
    * 1? = Menggunakan harga Retail
    * 2? = Menggunakan harga Grosir A
    * 3? = Menggunakan harga Grosir B
    */
//    var priceAltSwalayan: Int?,

    //	@ManyToOne
    //	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
    //	private FCustomerGroup fcustomerGroupBean;
    var fcustomerGroupBean: Int? =0,

    //	@ManyToOne
    //	@JoinColumn(name="fsubAreaBean", referencedColumnName="ID")
    //	private FSubArea fsubAreaBean;
    var fsubAreaBean: Int? =0,

    /*
    * CLASSIFIKASI MATERIAL & SALES
    * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
    * jika Division? = All Division maka exclusiveDivisionView tidak berlaku
    */
    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    var fdistributionChannelBean: Int? =0,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean: Int? =0,

    /*
    * reject promotion rules setting
    */
    var isNoPromotionRules: Boolean? =false,

    /*
    * Sales Covered
    * dan Jadwal Kunjungan
    */
    var isExclusiveSalesman: Boolean? =false,

    var stared: Boolean? = false,
    var unread: Boolean? = true,
    var selected: Boolean? = false,

//    @Ignore
//    var notes: String? ="",
    var created : Date? = Date(),
    var modified : Date? = Date(),
    var modifiedBy: String? ="", //User ID

//    @Ignore
//    var isStared: Boolean? =false,
//
//    @Ignore
//    var isUnread: Boolean? =false,
//
//    @Ignore
//    var isSelected: Boolean? =false,

): ModelEntity(), Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}


internal fun FCustomerEntity.toDomain(): FCustomer {
    return FCustomer(
        id = id,
        custno = custno,
        isOutletActive = isOutletActive,
        isFlagNewItem = isFlagNewItem!!,

        fdivisionBean = fdivisionBean?.let { FDivision(fdivisionBean) },
        custname = custname,
        isPkp = isPkp!!,
        namaPrshFakturPajak = namaPrshFakturPajak!!,
        alamatPrshFakturPajak = alamatPrshFakturPajak!!,
        npwp = npwp!!,
        tanggalPengukuhanPkp = tanggalPengukuhanPkp,
        tunaikredit = tunaikredit!!,
        lamaCredit = lamaCredit,
        creditlimit = creditlimit,
        maxInvoice = maxInvoice,
        namaPemilik = namaPemilik!!,
        address1 = address1,
        address2 = address2,
        city1 = city1,
        state1 = state1,
        phone1 = phone1!!,
        postcode = postcode!!,
        email = email!!,
        whatsApp = whatsApp!!,
        isStatusActive = isStatusActive!!,
        isNoeffcall = isNoeffcall!!,
        latitude = latitude!!,
        longitude = longitude!!,
        basicDisc1Barang = basicDisc1Barang!!,
        basicDisc1PlusBarang = basicDisc1PlusBarang!!,
        isDisc1RegManual = isDisc1RegManual!!,
        isDiscPlusRegManual = isDiscPlusRegManual!!,
        fcustomerGroupBean = fcustomerGroupBean?.let { FCustomerGroup(fcustomerGroupBean!!) },
        fsubAreaBean = fsubAreaBean?.let { FSubArea(fsubAreaBean!!) },

        fdistributionChannelBean = fdistributionChannelBean,
        ftPriceAlthBean = ftPriceAlthBean,

        isNoPromotionRules = isNoPromotionRules!!,
        isExclusiveSalesman = isExclusiveSalesman!!,


        stared = stared,
        unread = unread,
        selected = selected,

        created = created!!,
        modified = modified!!,
        modifiedBy = modifiedBy!!

    )
}


data class FCustomerWithFDivision(
    @Embedded  val fCustomerEntity: FCustomerEntity,
    @Relation(
        parentColumn = "fdivisionBean",
        entityColumn = "id"
    )
    val fDivisionEntity: FDivisionEntity?

)

data class FCustomerWithGroup(
    @Embedded  val fCustomerEntity: FCustomerEntity,
    @Relation(
        parentColumn = "fcustomerGroupBean",
        entityColumn = "id"
    )
    val fCustomerGroupEntity: FCustomerGroupEntity?
)

data class FCustomerWithFDivisionAndGroup(
    @Embedded  val fCustomerEntity: FCustomerEntity,

    @Relation(
        parentColumn = "fdivisionBean",
        entityColumn = "id"
    )
    val fDivisionEntity: FDivisionEntity?,

    @Relation(
        parentColumn = "fcustomerGroupBean",
        entityColumn = "id"
    )
    val fCustomerGroupEntity: FCustomerGroupEntity?
)
