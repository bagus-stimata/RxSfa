package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

//@Entity(tableName = "ftjobd_items")
@Entity(tableName = "FtJobdItems")
class FtJobdItems : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    //Digunakan untuk membentuk Array
    //BARIS
    var noUrut = 0

    //KOLOM
    var kolom = 0

    //CONTENT
    var contentString1 = ""
    var contentString2 = ""
    var contentInt1 = 0
    var contentInt2 = 0
    var contentDouble1 = 0.0
    var contentDouble2 = 0.0
    var isContentBol1 = false
    var isContentBol2 = false
    var contentTime1 = Date()
    var contentTime2 = Date()
    var notes = ""

    @Ignore
    var tempDouble1 = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftJobhBean", referencedColumnName="refno")
    //	private FtJobh ftJobhBean;
    var ftJobhBean = 0
}