package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumCurrency
import com.erp.distribution.sfa.model.modelenum.EnumTipePajakCustomer
import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit
import java.util.*

//@Entity(tableName = "fcustomer")
@Entity(tableName = "fCustomer")
class FCustomer(
    @PrimaryKey
    var id: Int,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Int,
    var custno: String,
    var isOutletActive: Boolean,
    var oldKode1: String,
    var isFlagNewItem: Boolean,

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean: Int,

    /*
    * Fungsinya: Jika menarik data dari sistem lain dimana mempunyai kode customer yang berbeda
    * Maka akan di konversikan menjadi kode pada sistem
    * Pada import data: ada opsi pilih mapping customer yang mana
    */
    var mappingInCode1: String,
    var mappingInCode2: String,
    var mappingInCode3: String,

    /*
    * Digunanakan untuk menghasilkan kode yang berbeda jika di extract
    * Fungsinya: Untuk Integrasi dengan Sistem Lain jika ternyata kode customer berbeda
    */
    var mappingOutCode1: String,
    var mappingOutCode2: String,
    var custGroupPromo1: Int, //GROUP PROMO
    var custGroupPromo2: Int, //GROUP PROMO
    var custname: String,
    var currency: EnumCurrency = EnumCurrency.IDR,

    /*
    * PERPAJAKAN / TAX
    */
    var isPkp: Boolean,
    var namaPrshFakturPajak: String,
    var alamatPrshFakturPajak: String,
    var namaPengusahaKenaPajak: String,
    var nikPajak: String,
    var npwp: String,
    var tanggalPengukuhanPkp : Date = Date(),
    var tipePajakCustomer: EnumTipePajakCustomer = EnumTipePajakCustomer.REG_01,
    var tunaikredit: EnumTunaiKredit = EnumTunaiKredit.T,
    var lamaCredit: Int,
    var creditlimit: Int,
    var maxInvoice: Int,
    var namaPemilik: String,
    var address1: String,
    var address2: String,
    var address3: String,
    var city1: String,
    var city2: String,
    var state1: String,
    var phone1: String,
    var phone2: String,
    var postcode: String,
    var email: String,
    var whatsApp: String,
    var isStatusActive: Boolean,

    //Tidak akan dipkai: 
    var harikunjungan: Int,
    var pekankunjungan: Int,
    var isNoeffcall: Boolean,
    var latitude: Int,
    var longitude: Int,

    /*
    * sementara belum dipakai sampai tahu principal atau SAP
    */
    //SHIPTO BILLTO	
    //	@Column(name="SHIPTOBILLTO", length=20)
    //	private String shipToBillTo ="";
    //	@Column(name="BILLTO", length=20)
    //	private String billTo ="";
    var basicDisc1Barang: Int,
    var basicDisc1PlusBarang: Int,
    var isDisc1RegManual: Boolean,
    var isDiscPlusRegManual: Boolean,

    /*
    * 0 = Menggunakan Harga Reguler Distributor (tidak mengenal harga bertingkat)
    * 1 = Menggunakan harga Retail
    * 2 = Menggunakan harga Grosir A
    * 3 = Menggunakan harga Grosir B
    */
    var priceAltSwalayan: Int,

    //	@ManyToOne
    //	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
    //	private FCustomerGroup fcustomerGroupBean;
    var fcustomerGroupBean: Int,

    //	@ManyToOne
    //	@JoinColumn(name="fsubAreaBean", referencedColumnName="ID")
    //	private FSubArea fsubAreaBean;
    var fsubAreaBean: Int,

    /*
    * CLASSIFIKASI MATERIAL & SALES
    * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
    * jika Division = All Division maka exclusiveDivisionView tidak berlaku
    */
    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    var fdistributionChannelBean: Int,

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean: Int,

    /*
    * reject promotion rules setting
    */
    var isNoPromotionRules: Boolean,

    /*
    * Sales Covered
    * dan Jadwal Kunjungan
    */
    var isExclusiveSalesman: Boolean,

//    @Ignore
//    var notes: String,
    var created : Date = Date(),
    var modified : Date = Date(),
    var modifiedBy: String, //User ID

//    @Ignore
//    var isStared: Boolean,
//
//    @Ignore
//    var isUnread: Boolean,
//
//    @Ignore
//    var isSelected: Boolean,

)