package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fdivision")
@Entity(tableName = "fDivision")
class FDivision (
    @PrimaryKey
    var id : Int, 

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID : Int, 
    var kode1 : String, 
    var kode2 : String, 
    var description : String, 
    var isDiffCompanyAccount : Boolean, 

    //	@ManyToOne
    //	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID", nullable=true)
    //    private AccCostCenter accCostCenterBean;
    var accCostCenterBean : Int, 

    /*
    * Common Division Parameter
    */
    //	@Column(name="USE_OWN_NOMOR_URUT_MASTER")
    //	private boolean useOwnNomorUrutMaster=false;
    //	@Column(name="USE_OWN_NOMOR_URUT_TRANSAKSI")
    //	private boolean useOwnNomorUrutTransaksi=false;
    var isUseNomorUrutMaterialToCompany : Boolean, 
    var isUseNomorUrutCustomerToCompany : Boolean, 
    var isUseNomorUrutVendorToCompany : Boolean, 
    var isUseNomorUrutTransaksiToCompany : Boolean, 
    var isUseNomorUrutJurnalToCompany : Boolean, 

    /*
    * Untuk Warehouse, Salesman Wajib Jadi satu dalam company
    */
    var isShareMaterialToCompany : Boolean, 
    var isShareMaterialOrgToCompany : Boolean, 
    var isShareCustomerToCompany : Boolean, 
    var isShareCustomerOrgToCompany : Boolean, 
    var isShareSalesmanToCompany : Boolean, 
    var isShareWarehouseToCompany : Boolean, 
    var isShareVendorToCompany : Boolean, 

    /*
    * General Ledger Accounting: Default True
    */
    var isShareCoaToCompany : Boolean, 
    var isShareCoaOrgToCompany : Boolean, 

    /*
    * TRANSAKSI: Default false
    */
    var isShareTransaksiToCompany : Boolean, 

    /*
    * DISKON PROMO DAN DISKON NOTA: Menggunakan Aturan Divisi
    * 
    */
    var isSharePromotionRulesToCompany : Boolean, 
    var isShareDiskonNotaToCompany : Boolean, 

    /*
    * ACCOUNTING
    */
    var isUserOnlyRead_HisDivision_Coa_WhenInput : Boolean, 
    var isNoTax_Trans : Boolean, 

    //	@ManyToOne
    //	@JoinColumn(name="fcompanyBean", referencedColumnName="ID")
    //	private FCompany fcompanyBean;
    var fcompanyBean : Int, 

    /*
    * ****************************
    */
    var isStatusActive : Boolean, 
    var isWebServiceActive : Boolean, 

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
    //	private boolean nomorUrutDocTransFollowCorp : Boolean, ;
    /*
	 * Urutan master product, material, salesman, mengikuti parameter level 2. Level Perusahaan
	 */
    var isSysvarNomorUrutMasterFollowCorp : Boolean, 
    var isSysvarFormatFakturFollowCorp : Boolean, 

    //	/*
    //	 * FORMAT FAKTUR DAN ALAMAT
    //	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1 : String, ;
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1 : String, ;
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1 : String, ;
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1 : String, ;
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Mengikuti Mengikuti Corporation	
 */
    //	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
    //	private String invoiceCompanyNpwpPhone1 : String, ;
    var created: Date = Date(), 
    var modified: Date = Date(), 
    var modifiedBy : String  //User ID
)