package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumCurrency
import com.erp.distribution.sfa.model.modelenum.EnumTipePajakCustomer
import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit
import java.util.*

//@Entity(tableName = "fcustomer")
@Entity(tableName = "FCustomer")
class FCustomer{
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = 0
    var custno = ""
    var isOutletActive = false
    var oldKode1 = ""
    var isFlagNewItem = false

    //	@ManyToOne
    //	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
    //	private FDivision fdivisionBean;
    var fdivisionBean = 0

    /*
    * Fungsinya: Jika menarik data dari sistem lain dimana mempunyai kode customer yang berbeda
    * Maka akan di konversikan menjadi kode pada sistem
    * Pada import data: ada opsi pilih mapping customer yang mana
    */
    var mappingInCode1 = ""
    var mappingInCode2 = ""
    var mappingInCode3 = ""

    /*
    * Digunanakan untuk menghasilkan kode yang berbeda jika di extract
    * Fungsinya: Untuk Integrasi dengan Sistem Lain jika ternyata kode customer berbeda
    */
    var mappingOutCode1 = ""
    var mappingOutCode2 = ""
    var custGroupPromo1 = 0 //GROUP PROMO
    var custGroupPromo2 = 0 //GROUP PROMO
    var custname = ""
    var currency: EnumCurrency = EnumCurrency.IDR

    /*
    * PERPAJAKAN / TAX
    */
    var isPkp = true
    var namaPrshFakturPajak = ""
    var alamatPrshFakturPajak = ""
    var namaPengusahaKenaPajak = ""
    var nikPajak = ""
    var npwp = ""
    var tanggalPengukuhanPkp = Date()
    var tipePajakCustomer: EnumTipePajakCustomer = EnumTipePajakCustomer.REG_01
    var tunaikredit: EnumTunaiKredit = EnumTunaiKredit.T
    var lamaCredit = 0
    var creditlimit = 0.0
    var maxInvoice = 0
    var namaPemilik = ""
    var address1 = ""
    var address2 = ""
    var address3 = ""
    var city1 = ""
    var city2 = ""
    var state1 = ""
    var phone1 = ""
    var phone2 = ""
    var postcode = ""
    var email = ""
    var whatsApp = ""
    var isStatusActive = false

    //Tidak akan dipkai: 
    var harikunjungan = 0
    var pekankunjungan = 0
    var isNoeffcall = false
    var latitude = 0.0
    var longitude = 0.0

    /*
    * sementara belum dipakai sampai tahu principal atau SAP
    */
    //SHIPTO BILLTO	
    //	@Column(name="SHIPTOBILLTO", length=20)
    //	private String shipToBillTo ="";
    //	@Column(name="BILLTO", length=20)
    //	private String billTo ="";
    var basicDisc1Barang = 0.0
    var basicDisc1PlusBarang = 0.0
    var isDisc1RegManual = false
    var isDiscPlusRegManual = false

    /*
    * 0 = Menggunakan Harga Reguler Distributor (tidak mengenal harga bertingkat)
    * 1 = Menggunakan harga Retail
    * 2 = Menggunakan harga Grosir A
    * 3 = Menggunakan harga Grosir B
    */
    var priceAltSwalayan = 0

    //	@ManyToOne
    //	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
    //	private FCustomerGroup fcustomerGroupBean;
    var fcustomerGroupBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="fsubAreaBean", referencedColumnName="ID")
    //	private FSubArea fsubAreaBean;
    var fsubAreaBean = 0

    /*
    * CLASSIFIKASI MATERIAL & SALES
    * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
    * jika Division = All Division maka exclusiveDivisionView tidak berlaku
    */
    //	@ManyToOne
    //	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
    //	private FDistributionChannel fdistributionChannelBean;
    var fdistributionChannelBean = 0

    //	@ManyToOne
    //	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
    //	private FtPriceAlth ftPriceAlthBean;
    var ftPriceAlthBean = 0

    /*
    * reject promotion rules setting
    */
    var isNoPromotionRules = false

    /*
    * Sales Covered
    * dan Jadwal Kunjungan
    */
    var isExclusiveSalesman = false

    @Ignore
    var notes = ""
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID

    @Ignore
    var isStared = false

    @Ignore
    var isUnread = false

    @Ignore
    var isSelected = false
}