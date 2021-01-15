package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fdivision")
@Entity(tableName = "FDivision")
class FDivision {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = 0
    var kode1 = ""
    var kode2 = ""
    var description = ""
    var isDiffCompanyAccount = false

    //	@ManyToOne
    //	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID", nullable=true)
    //    private AccCostCenter accCostCenterBean;
    var accCostCenterBean = 0

    /*
    * Common Division Parameter
    */
    //	@Column(name="USE_OWN_NOMOR_URUT_MASTER")
    //	private boolean useOwnNomorUrutMaster=false;
    //	@Column(name="USE_OWN_NOMOR_URUT_TRANSAKSI")
    //	private boolean useOwnNomorUrutTransaksi=false;
    var isUseNomorUrutMaterialToCompany = false
    var isUseNomorUrutCustomerToCompany = false
    var isUseNomorUrutVendorToCompany = false
    var isUseNomorUrutTransaksiToCompany = true
    var isUseNomorUrutJurnalToCompany = true

    /*
    * Untuk Warehouse, Salesman Wajib Jadi satu dalam company
    */
    var isShareMaterialToCompany = false
    var isShareMaterialOrgToCompany = false
    var isShareCustomerToCompany = false
    var isShareCustomerOrgToCompany = false
    var isShareSalesmanToCompany = false
    var isShareWarehouseToCompany = false
    var isShareVendorToCompany = false

    /*
    * General Ledger Accounting: Default True
    */
    var isShareCoaToCompany = true
    var isShareCoaOrgToCompany = true

    /*
    * TRANSAKSI: Default false
    */
    var isShareTransaksiToCompany = false

    /*
    * DISKON PROMO DAN DISKON NOTA: Menggunakan Aturan Divisi
    * 
    */
    var isSharePromotionRulesToCompany = false
    var isShareDiskonNotaToCompany = false

    /*
    * ACCOUNTING
    */
    var isUserOnlyRead_HisDivision_Coa_WhenInput = false
    var isNoTax_Trans = false

    //	@ManyToOne
    //	@JoinColumn(name="fcompanyBean", referencedColumnName="ID")
    //	private FCompany fcompanyBean;
    var fcompanyBean = 0

    /*
    * ****************************
    */
    var isStatusActive = true
    var isWebServiceActive = false

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
    //	private boolean nomorUrutDocTransFollowCorp = true;
    /*
	 * Urutan master product, material, salesman, mengikuti parameter level 2. Level Perusahaan
	 */
    var isSysvarNomorUrutMasterFollowCorp = false
    var isSysvarFormatFakturFollowCorp = false

    //	/*
    //	 * FORMAT FAKTUR DAN ALAMAT
    //	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1 = "";
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1 = "";
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1 = "";
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1 = "";
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Mengikuti Mengikuti Corporation	
 */
    //	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
    //	private String invoiceCompanyNpwpPhone1 = "";
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}