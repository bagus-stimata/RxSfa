package com.erp.distribution.sfa.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erp.distribution.sfa.data.source.entity.FStockEntity
import com.erp.distribution.sfa.domain.model.FArea
import com.erp.distribution.sfa.domain.model.FDivision
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class FStock (
    @PrimaryKey
    var refno: Long =0,

    /*
    * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
    * keperluan diantaranya:
    * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
    * 2. 
    */
    var sourceID: Long =0,

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
    //	@Column(name="QTY_IN")
    //	private Integer qtyIn =0.0;
    //	@Column(name="QTY_OUT")
    //	private Integer qtyOut =0.0;
    var qtyIn : Double = 0.0,
    var qtyOut : Double =0.0,

    //	@Column(name="QTY_ADJUST", length=10)	
    //	private Integer qtyAdjust =0.0;
    //	@Column(name="SALDO_AKHIR")
    //	private Integer saldoAkhir =0.0;
    var qtyAdjust : Double =0.0,
    var saldoAkhir : Double =0.0,

    //	@ManyToOne
    //	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID")
    //	private FWarehouse fwarehouseBean;
    var fwarehouseBean : Int =0,

    //	@ManyToOne
    //	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
    //	private FMaterial fmaterialBean;
    var fmaterialBean : Int =0,
): Parcelable

internal fun FStock.toEntity(): FStockEntity {
    return FStockEntity(
            refno = refno,
            stockDate = stockDate,
            fmaterialBean = fmaterialBean,
            fwarehouseBean = fwarehouseBean,
            saldoAwal = saldoAwal,
            saldoAkhir = saldoAkhir
    )
}
