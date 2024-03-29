package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import com.erp.distribution.sfa.data.source.entity.FCustomerEntity
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumCurrency
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTipePajakCustomer
import com.erp.distribution.sfa.data.source.entity.modelenum.EnumTunaiKredit
import com.erp.distribution.sfa.domain.base.Model
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.text.DateFormat
import java.util.*


@Parcelize
data class FCustomer(
        var id: Int =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceId: Int =0,
        var custno: String ="",
        var outletActive: Boolean =false,
        var flagNewItem: Boolean =false,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
//    var fdivisionBean: Int =0,
        var fdivisionBean: FDivision? =FDivision(),


    /*
    * Fungsinya: Jika menarik data dari sistem lain dimana mempunyai kode customer yang berbeda
    * Maka akan di konversikan menjadi kode pada sistem
    * Pada import data: ada opsi pilih mapping customer yang mana
    */
//    var mappingInCode1: String ="",
//    var mappingInCode2: String ="",
//    var mappingInCode3: String ="",

    /*
    * Digunanakan untuk menghasilkan kode yang berbeda jika di extract
    * Fungsinya: Untuk Integrasi dengan Sistem Lain jika ternyata kode customer berbeda
    */
//    var mappingOutCode1: String ="",
//    var mappingOutCode2: String ="",
//    var custGroupPromo1: Int =0, //GROUP PROMO
//    var custGroupPromo2: Int =0, //GROUP PROMO
        var custname: String ="",
        var currency: EnumCurrency = EnumCurrency.IDR,

    /*
    * PERPAJAKAN / TAX
    */
    var pkp: Boolean =false,
        var namaPrshFakturPajak: String ="",
        var alamatPrshFakturPajak: String ="",
        var namaPengusahaKenaPajak: String ="",
        var nikPajak: String ="",
        var npwp: String ="",
        var tanggalPengukuhanPkp : Date? = Date(),
        var tipePajakCustomer: EnumTipePajakCustomer = EnumTipePajakCustomer.REG_01,
        var tunaikredit: EnumTunaiKredit = EnumTunaiKredit.T,
        var lamaCredit: Int =0,
        var creditlimit: Int =0,
        var maxInvoice: Int =0,
        var namaPemilik: String ="",
        var address1: String ="",
        var address2: String ="",
        var address3: String ="",
        var city1: String ="",
        var city2: String ="",
        var state1: String ="",
        var phone1: String ="",
        var phone2: String ="",
        var postcode: String ="",
        var email: String ="",
        var whatsApp: String ="",
        var statusActive: Boolean =false,

    //Tidak akan dipkai: 
        var harikunjungan: Int =0,
        var pekankunjungan: Int =0,
        var noeffcall: Boolean =false,
        var latitude: Int =0,
        var longitude: Int =0,

    /*
    * sementara belum dipakai sampai tahu principal atau SAP
    */
    //SHIPTO BILLTO	
    //	@Column(name="SHIPTOBILLTO", length=20)
    //	private String shipToBillTo ="";
    //	@Column(name="BILLTO", length=20)
    //	private String billTo ="";
        var basicDisc1Barang: Int =0,
        var basicDisc1PlusBarang: Int =0,
        var disc1RegManual: Boolean =false,
        var discPlusRegManual: Boolean =false,

    /*
    * 0 = Menggunakan Harga Reguler Distributor (tidak mengenal harga bertingkat)
    * 1 = Menggunakan harga Retail
    * 2 = Menggunakan harga Grosir A
    * 3 = Menggunakan harga Grosir B
    */
//    var priceAltSwalayan: Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
    //	private FCustomerGroup fcustomerGroupBean;
//        var fcustomerGroupBean: Int =0,
        var fcustomerGroupBean: FCustomerGroup? = FCustomerGroup(),

    //	@ManyToOne
    //	@JoinColumn(name="fsubAreaBean", referencedColumnName="ID")
    //	private FSubArea fsubAreaBean;
        var fsubAreaBean: FSubArea? = FSubArea(),

    /*
    * CLASSIFIKASI MATERIAL & SALES
    * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
    * jika Division = All Division maka exclusiveDivisionView tidak berlaku
    */
    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
        var fdistributionChannelBean: Int? =0,

        var ftSaleshSet : List<FtSalesh> = listOf<FtSalesh>(),


        //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
        var ftPriceAlthBean: Int? =0,

    /*
    * reject promotion rules setting
    */
    var noPromotionRules: Boolean =false,

    /*
    * Sales Covered
    * dan Jadwal Kunjungan
    */
    var exclusiveSalesman: Boolean? =false,

        var stared: Boolean? = false,
        var unread: Boolean? = true,
        var selected: Boolean? = false,

//    @Ignore
//    var notes: String ="",
        var created : Date = Date(),
        var modified : Date = Date(),
        var modifiedBy: String ="", //User ID

//    @Ignore
//    var isStared: Boolean =false,
//
//    @Ignore
//    var isUnread: Boolean =false,
//
//    @Ignore
//    var isSelected: Boolean =false,

): Model(), Parcelable, Serializable{
    constructor(theId: Int): this(id = theId)
    constructor(theId: Int, theCustno: String, theCustname: String): this(id = theId, custno=theCustno, custname = theCustname)

    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}

internal fun FCustomer.toEntity(): FCustomerEntity {
    return FCustomerEntity(
        id = id,
        custno = custno,
        outletActive = outletActive,
        flagNewItem = flagNewItem,

        fdivisionBean = fdivisionBean!!.id,
        custname = custname,
        pkp = pkp,
        namaPrshFakturPajak = namaPrshFakturPajak,
        alamatPrshFakturPajak = alamatPrshFakturPajak,
        npwp = npwp,
        tanggalPengukuhanPkp = tanggalPengukuhanPkp,
        tunaikredit = tunaikredit,
        lamaCredit = lamaCredit,
        creditlimit = creditlimit,
        maxInvoice = maxInvoice,
        namaPemilik = namaPemilik,
        address1 = address1,
        address2 = address2,
        city1 = city1,
        state1 = state1,
        phone1 = phone1,
        postcode = postcode,
        email = email,
        whatsApp = whatsApp,
        statusActive = statusActive,
        noeffcall = noeffcall,
        latitude = latitude,
        longitude = longitude,
        basicDisc1Barang = basicDisc1Barang,
        basicDisc1PlusBarang = basicDisc1PlusBarang,
        disc1RegManual = disc1RegManual,
        discPlusRegManual = discPlusRegManual,
        fcustomerGroupBean = fcustomerGroupBean!!.id,
        fsubAreaBean = fsubAreaBean!!.id,

        fdistributionChannelBean = fdistributionChannelBean,
        ftPriceAlthBean = ftPriceAlthBean,

        noPromotionRules = noPromotionRules,
        exclusiveSalesman = exclusiveSalesman,


        stared = stared,
        unread = unread,
        selected = selected,

        created = created,
        modified = modified,
        modifiedBy = modifiedBy

    )
}


