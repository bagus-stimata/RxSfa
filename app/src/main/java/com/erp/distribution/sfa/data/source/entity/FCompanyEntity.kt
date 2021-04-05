package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//@Entity(tableName = "fcompany")
@Entity(tableName = "fCompany")
data class FCompanyEntity (
        @PrimaryKey
        var id: Int,
        var kode1 : String,
        var kode2 : String,
        var description : String,
        var shareDataToBeClone: Boolean,
        var shareDataToBeCloneSecurityCode : String,
        var statusActive: Boolean,
        var webServiceActive: Boolean,

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
    //	private boolean nomorUrutDocTransFollowApp: Boolean,;
    /*
	 * FORMAT FAKTUR DAN ALAMAT
	 */
    //	@Column(name="INVOICE_COMP_NAME_1", length=75)
    //	private String invoiceCompanyName1 : String,;
    //	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
    //	private String invoiceCompanyAddress1 : String,;
    //	@Column(name="INVOICE_COMP_CITY_1", length=30)
    //	private String invoiceCompanyCity1 : String,;
    //	@Column(name="INVOICE_COMP_PHONE_1", length=25)
    //	private String invoiceCompanyPhone1 : String,;
    /*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Customer, Mengikuti Corporation	
 */
    //	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
    //	private String invoiceCompanyNpwpPhone1 : String,;
        var created : Date = Date(),
        var modified : Date = Date(),
        var modifiedBy : String //User ID

)

