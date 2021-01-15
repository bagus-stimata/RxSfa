package com.erp.distribution.sfa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.model.modelenum.EnumTipeSuratJalan
import java.io.Serializable
import java.util.*

//@Entity(tableName = "ftsalesh_historysj")
@Entity(tableName = "FtSaleshHistorySJ")
class FtSaleshHistorySJ : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var tipeSuratJalan: EnumTipeSuratJalan? = null
    var sjNumber = ""
    var sjDate = Date()
    var driverName = ""

    /*
    * Di pakai untuk klarifikasi di kemudian hari jika ada yang berusahan untuk menghapus atau melakukan upaya-upaya kecurangan
    * pada Surat Jalan Pengiriman
    */
    var totalPcsKirimTagih = 0

    //Total saat kirim
    var amountAfterDiscPlusRpAfterPpn = 0.0

    //	@ManyToOne
    //	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
    //	private FtSalesh ftSaleshBean;
    var ftSaleshBean = 0
}