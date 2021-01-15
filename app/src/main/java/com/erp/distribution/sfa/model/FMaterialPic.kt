package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

//@Entity(tableName = "fmaterial_pic")
@Entity(tableName = "FMaterialPic")
class FMaterialPic : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean")
    //	private FMaterial fmaterialBean;
    var fmaterialBean = 0

    /*
    * Nomor Urut:
    * 1 = adalah gambar produk depan (Utama)
    * 2 = adalah gambar produk sisi lain
    * 3 = adalah gambar produk sisi lain
    * 4 = aalah gambar produk sisi lain
    */
    var nomorUrut = 0

    //DOC, PIC, PDF
    var tipeFile = ""
    var imageName = ""
    var title = ""
    var description = ""

    /*
    * MOBILE = diupload dari mobile
    * APP_WEB = dari aplikasi Web
    */
    var uploadFrom = ""
    var imageCapturedBy = ""
    var created = Date()
    var modified = Date()
    var modifiedBy = "" //User ID
}