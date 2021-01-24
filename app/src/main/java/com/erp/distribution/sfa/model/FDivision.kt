package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fdivision")
@Entity(tableName = "fDivision")
class FDivision (
    @PrimaryKey
    var id : Int = -1,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID : Int =0, 
    var kode1 : String ="", 
    var kode2 : String ="", 
    var description : String ="", 
    var isDiffCompanyAccount : Boolean =false, 

    //	@ManyToOne
    //	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID", nullable=true)
    //    private AccCostCenter accCostCenterBean;
    var accCostCenterBean : Int =0, 

    /*
    * Common Division Parameter
    */
    //	@Column(name="USE_OWN_NOMOR_URUT_MASTER")
    //	private boolean useOwnNomorUrutMaster=false;
    //	@Column(name="USE_OWN_NOMOR_URUT_TRANSAKSI")
    //	private boolean useOwnNomorUrutTransaksi=false;
    var isUseNomorUrutMaterialToCompany : Boolean =false, 
    var isUseNomorUrutCustomerToCompany : Boolean =false, 
    var isUseNomorUrutVendorToCompany : Boolean =false, 
    var isUseNomorUrutTransaksiToCompany : Boolean =false, 
    var isUseNomorUrutJurnalToCompany : Boolean =false, 

    /*
    * Untuk Warehouse, Salesman Wajib Jadi satu dalam company
    */
    var isShareMaterialToCompany : Boolean =false, 
    var isShareMaterialOrgToCompany : Boolean =false, 
    var isShareCustomerToCompany : Boolean =false, 
    var isShareCustomerOrgToCompany : Boolean =false, 
    var isShareSalesmanToCompany : Boolean =false, 
    var isShareWarehouseToCompany : Boolean =false, 
    var isShareVendorToCompany : Boolean =false, 

    /*
    * General Ledger Accounting: Default True
    */
    var isShareCoaToCompany : Boolean =false, 
    var isShareCoaOrgToCompany : Boolean =false, 

    /*
    * TRANSAKSI: Default false
    */
    var isShareTransaksiToCompany : Boolean =false, 

    /*
    * DISKON PROMO DAN DISKON NOTA: Menggunakan Aturan Divisi
    * 
    */
    var isSharePromotionRulesToCompany : Boolean =false, 
    var isShareDiskonNotaToCompany : Boolean =false, 

    /*
    * ACCOUNTING
    */
    var isUserOnlyRead_HisDivision_Coa_WhenInput : Boolean =false, 
    var isNoTax_Trans : Boolean =false, 

    //	@ManyToOne
    //	@JoinColumn(name="fcompanyBean", referencedColumnName="ID")
    //	private FCompany fcompanyBean;
    var fcompanyBean : Int =0, 

    /*
    * ****************************
    */
    var isStatusActive : Boolean =false, 
    var isWebServiceActive : Boolean =false, 

    /*
    * SETTING YANG BERLAKU UNTUK SEMUA DIVISI
    * JIKA KOSONG MAKA MENGGUNAKAN PRIORITAS ATASNYA
    * 1. Parameter System
    * 2. Corporation
    * 3. Division 
    */
    /*
	 * Urutan nomor dalam satu divisi biasanya mengikuti perusahaan, karena mempunyai nomor urut faktur pajak yang sama
	 * Tidak digunakan. Karena urutan Dokumen Transaksi  mengikuti Company
	 */
    //	@Column(name="NOMOR_URUT_DOC_FOLLOW_CORP") //Tidak boleh diubah-ubah
    //	private boolean nomorUrutDocTransFollowCorp : Boolean =false, ;
    /*
	 * Urutan master product, material, salesman, mengikuti parameter level 2. Level Perusahaan
	 */
    var isSysvarNomorUrutMasterFollowCorp : Boolean =false, 
    var isSysvarFormatFakturFollowCorp : Boolean =false, 

    //	/*
    //	 * FORMAT FAKTUR DAN ALAMAT
    //	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1 : String ="", ;
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1 : String ="", ;
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1 : String ="", ;
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1 : String ="", ;
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Mengikuti Mengikuti Corporation	
 */
    //	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
    //	private String invoiceCompanyNpwpPhone1 : String ="", ;
    var created: Date? = Date(),
    var modified: Date? = Date(),
    var modifiedBy : String? =""  //User ID
)