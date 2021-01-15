package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fcompany")
@Entity(tableName = "fcompany")
class FCompany {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var kode1 = ""
    var kode2 = ""
    var description = ""
    var isShareDataToBeClone = false
    var shareDataToBeCloneSecurityCode = ""
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
	 * 
	 */
    //	@Column(name="NOMOR_URUT_DOC_FOLLOW_APP") //SELALU MENGIKUTI NOMOR URUT COMPANY
    //	private boolean nomorUrutDocTransFollowApp = true;
    /*
	 * FORMAT FAKTUR DAN ALAMAT
	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1 = "";
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1 = "";
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1 = "";
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1 = "";
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Customer, Mengikuti Corporation	
 */
    //	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
    //	private String invoiceCompanyNpwpPhone1 = "";
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}