/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.distribution.sfa.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "sysvar")
data class SysvarEntity  (
    @PrimaryKey(autoGenerate =true,)
    var id: Int =0,
    var sysvarId :String ="",

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceId :String ="",
    var nomorUrut :Int =0,
    var isVisible :Boolean =true,
    var groupSysvar :String ="",
    var deskripsi :String ="",
    var notes :String ="",
    var tipeData :String ="",
    var lenghtData :Int =0,
    var prefix :String ="",
    var suffix :String ="",
    var nilaiString1 :String ="",
    var nilaiString2 :String ="",
    var nilaiBol1 :Boolean =false,
    var nilaiBol2 :Boolean =false,
    var nilaiInt1 :Int =0,
    var nilaiInt2 :Int =0,
    var nilaiDouble1 :Double =0.0,
    var nilaiDouble2 :Double =0.0,
    var nilaiDate1: Date =Date(),
    var nilaiDate2: Date =Date(),
    var nilaiTime1: Date =Date(),
    var nilaiTime2: Date =Date(),

    /*
    * DIPAKAI UNTUK 
    * Level 1= Level Aplikasi
    * Level 2= Level Company
    * Level 3= Level Division
    */
    //	private FCompany fcompanyBean;
    var fcompanyBean: Int = 0,

    //	private FDivision fdivisionBean;
    var fdivisionBean: Int = 0,
    var created:Date = Date(),
    var modified:Date = Date(),
    var modifiedBy :String ="" //User ID

): Serializable