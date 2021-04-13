package com.erp.distribution.sfa.data.source.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.domain.model.FArea
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FStock
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "fStock")
data class FStockEntity (
    @PrimaryKey
    var refno: Long =0,

    /*
    * HARI YANG DIPAKAI JUGA PADA HPP
    */
    var stockDate: Date = Date(),

    //	@Column(name="SALDO_AWAL")
    //	private Integer saldoAwal =0.0;
    var saldoAwal : Double = 0.0,

    /*
    * NEXT AKAN DIBIKIN TRANSIEN
    */

    //	@Column(name="QTY_ADJUST", length=10)	
    //	private Integer qtyAdjust =0.0;
    //	@Column(name="SALDO_AKHIR")
    //	private Integer saldoAkhir =0.0;
    var saldoAkhir : Double =0.0,

    /*
    * HARGA BELI NET TERAKHIR
    * TAPI HARGA BELI TERAKHIR INI MENGGUNAKAN PPN (after PPN)
    */
//    var closingPprice2_AfterPpn : Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean;
    var fwarehouseBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean : Int =0,
): Parcelable

internal fun FStockEntity.toDomain(): FStock {
    return FStock(
            refno = refno,
            stockDate = stockDate,
            fmaterialBean = fmaterialBean,
            fwarehouseBean = fwarehouseBean,
            saldoAwal = saldoAwal,
            saldoAkhir = saldoAkhir
    )
}
