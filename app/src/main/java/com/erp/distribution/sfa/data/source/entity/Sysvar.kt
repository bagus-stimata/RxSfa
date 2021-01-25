/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "sysvar")
class Sysvar  {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0
    var sysvarId = ""

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID = ""
    var nomorUrut = 0
    var isVisible = true
    var groupSysvar = ""
    var deskripsi = ""
    var notes = ""
    var tipeData = ""
    var lenghtData = 0
    var prefix = ""
    var suffix = ""
    var nilaiString1 = ""
    var nilaiString2 = ""
    var nilaiBol1 = false
    var nilaiBol2 = false
    var nilaiInt1 = 0
    var nilaiInt2 = 0
    var nilaiDouble1 = 0.0
    var nilaiDouble2 = 0.0
    var nilaiDate1: Date? = null
    var nilaiDate2: Date? = null
    var nilaiTime1: Date? = null
    var nilaiTime2: Date? = null

    /*
    * DIPAKAI UNTUK 
    * Level 1= Level Aplikasi
    * Level 2= Level Company
    * Level 3= Level Division
    */
    //	private FCompany fcompanyBean;
    var fcompanyBean: Int? = null

    //	private FDivision fdivisionBean;
    var fdivisionBean: Int? = null
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID

    //** End Tools
    override fun toString(): String {
        return id.toString() + ""
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + if (fcompanyBean == null) 0 else fcompanyBean.hashCode()
        result = prime * result + if (fdivisionBean == null) 0 else fdivisionBean.hashCode()
        result = prime * result + if (id == null) 0 else id.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj == null) {
            return false
        }
        if (obj !is Sysvar) {
            return false
        }
        val other = obj
        if (fcompanyBean == null) {
            if (other.fcompanyBean != null) {
                return false
            }
        } else if (fcompanyBean != other.fcompanyBean) {
            return false
        }
        if (fdivisionBean == null) {
            if (other.fdivisionBean != null) {
                return false
            }
        } else if (fdivisionBean != other.fdivisionBean) {
            return false
        }
        if (id == null) {
            if (other.id != null) {
                return false
            }
        } else if (id != other.id) {
            return false
        }
        return true
    }

    companion object {
        const val serialVersionUID = 1L
    }
}